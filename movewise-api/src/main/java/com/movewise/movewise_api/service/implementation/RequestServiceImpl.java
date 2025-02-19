package com.movewise.movewise_api.service.implementation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.movewise.movewise_api.entity.AssignmentGroup;
import com.movewise.movewise_api.entity.GroupMember;
import com.movewise.movewise_api.entity.Item;
import com.movewise.movewise_api.entity.Request;
import com.movewise.movewise_api.entity.Transportation;
import com.movewise.movewise_api.entity.User;
import com.movewise.movewise_api.entity.enumberable.RequestStatus;
import com.movewise.movewise_api.entity.enumberable.Role;
import com.movewise.movewise_api.entity.enumberable.Status;
import com.movewise.movewise_api.entity.enumberable.TransportationStatus;
import com.movewise.movewise_api.exception.CustomException;
import com.movewise.movewise_api.model.request.request.RequestRequest;
import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.model.response.request.RequestResponse;
import com.movewise.movewise_api.repository.ItemRepository;
import com.movewise.movewise_api.repository.RequestRepository;
import com.movewise.movewise_api.repository.TransportationRepository;
import com.movewise.movewise_api.repository.UserRepository;
import com.movewise.movewise_api.service.RequestService;
import com.movewise.movewise_api.service.UserService;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private UserService userService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private TransportationRepository transportationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Create Request
    @Override
    public ResultResponse<RequestResponse> createRequest(RequestRequest request) {
        try {
            // Get registered user information
            User user = userService.findUserByJwt();

            // Check itemIds
            if (request.itemIds == null || request.itemIds.isEmpty()) {
                return ResultResponse.fail("Cannot find customer items!", HttpStatus.BAD_REQUEST.value());
            }

            // Initialize fail message
            List<String> validationMessages = new ArrayList<>();

            // Fetch Item By Ids
            List<Item> items = itemRepository.findAllByIdIn(request.itemIds);

            // Request Validations
            if (items.isEmpty()) {
                throw new CustomException("No items found for the provided IDs!", HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (request.TransportedDate == null) {
                validationMessages.add("Transported date is required!");
            } else if (request.serviceId == null) {
                validationMessages.add("Please select any of the viable services!");
            } else if (request.requestCustomerInfo.fullName.isEmpty()) {
                validationMessages.add("Full Name is required!");
            } else if (request.requestCustomerInfo.phone.isEmpty()) {
                validationMessages.add("Phone Number is required!");
            } else if (request.requestCustomerInfo.gender == null) {
                validationMessages.add("Gender is required!");
            } else if (request.requestCustomerInfo.fetchAddress.isEmpty()) {
                validationMessages.add("Fetch Address is required!");
            } else if (request.requestCustomerInfo.fetchCity.isEmpty()) {
                validationMessages.add("Fetch City is required!");
            } else if (request.requestCustomerInfo.deliverAddress.isEmpty()) {
                validationMessages.add("Deliver Address is required!");
            } else if (request.requestCustomerInfo.deliverCity.isEmpty()) {
                validationMessages.add("Deliver City is required!");
            }

            // If there are validation errors, return fail response
            if (!validationMessages.isEmpty()) {
                return ResultResponse.fail(validationMessages, HttpStatus.BAD_REQUEST.value());
            }

            // Map request
            Request newRequest = modelMapper.map(request, Request.class);
            if (newRequest == null) {
                throw new CustomException("Cannot find customer request!", HttpStatus.BAD_REQUEST);
            }

            newRequest.setCustomer(user);
            newRequest.setItems(items);
            newRequest.setRequestStatus(RequestStatus.PENDING);

            // Save new request
            Request savedRequest = requestRepository.save(newRequest);

            RequestResponse requestResponse = modelMapper.map(savedRequest, RequestResponse.class);
            return ResultResponse.success(requestResponse,
                    "Request created successfully, please wait for the administrator to verify!",
                    HttpStatus.CREATED.value());
        } catch (CustomException e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            return ResultResponse.fail(errorMessages, e.getStatus().value());
        } catch (Exception e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("An unexpected error occurred: " + e.getMessage());
            return ResultResponse.fail(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    // Update request status
    @Override
    public ResultResponse<RequestResponse> updateRequestStatus(UUID requestId, RequestStatus status,
            String adminResponse) {
        try {
            Optional<Request> requestOptional = requestRepository.findById(requestId);
            if (requestOptional.isEmpty()) {
                throw new CustomException("Request not found!", HttpStatus.NOT_FOUND);
            }

            Request request = requestOptional.get();

            // Pending status change list
            List<RequestStatus> pendingChangeList = new ArrayList<>();
            pendingChangeList.add(RequestStatus.CANCELLED);
            pendingChangeList.add(RequestStatus.REJECTED);

            // Assigned status change list
            List<RequestStatus> assignedChangeList = new ArrayList<>();
            assignedChangeList.add(RequestStatus.CANCELLED);

            boolean isChanged = false;
            if (request.getRequestStatus() == RequestStatus.PENDING && pendingChangeList.contains(status)) {
                request.setRequestStatus(status);
                request.setAdminResponse(adminResponse);
                isChanged = true;
            } else if (request.getRequestStatus() == RequestStatus.ASSIGNED && assignedChangeList.contains(status)) {
                request.setRequestStatus(status);
                request.setAdminResponse(adminResponse);
                isChanged = true;
            }

            if (isChanged) {
                // Save request
                Request savedRequest = requestRepository.save(request);

                RequestResponse requestResponse = modelMapper.map(savedRequest, RequestResponse.class);
                return ResultResponse.success(requestResponse,
                        "Update request status successfully!",
                        HttpStatus.OK.value());
            } else {
                // Throw error
                throw new CustomException(
                        "Cannot change Request status from " + request.getRequestStatus() + " to " + status + "!",
                        HttpStatus.BAD_REQUEST);
            }

        } catch (CustomException e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            return ResultResponse.fail(errorMessages, e.getStatus().value());
        } catch (Exception e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("An unexpected error occurred: " + e.getMessage());
            return ResultResponse.fail(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    // Validate TransportedDate
    public boolean validateTransportedDate(LocalDateTime transportedDate) {
        try {
            // Check transportedDate must be 3 days ahead of booking date
            LocalDateTime midnightToday = LocalDateTime.now().with(LocalTime.MIDNIGHT);
            LocalDateTime minimumAllowedDate = midnightToday.plusDays(3);
            return !transportedDate.isBefore(minimumAllowedDate);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Cannot validate transported date!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResultResponse<RequestResponse> assignAssignmentGroup(UUID requestId, String adminResponse,
            List<UUID> memberIds, UUID transportId) {
        try {
            Request request = requestRepository.findById(requestId)
                    .orElseThrow(() -> new CustomException("Request not found!", HttpStatus.NOT_FOUND));

            if (request.getRequestStatus() != RequestStatus.PENDING) {
                throw new CustomException("Request can only be assigned when in pending state!",
                        HttpStatus.BAD_REQUEST);
            }

            Transportation transportation = transportationRepository.findById(transportId)
                    .orElseThrow(() -> new CustomException("Transport not found!", HttpStatus.NOT_FOUND));

            if (transportation.getTransportationStatus() == TransportationStatus.INAVAILABLE) {
                throw new CustomException("Transport not currently available!", HttpStatus.BAD_REQUEST);
            }

            LocalDateTime transportDate = request.getTransportedDate();
            List<Request> requestList = requestRepository.findByTransportedDate(transportDate);

            // Check if transport is assigned on transport day
            Set<UUID> transportSet = requestList.stream()
                    .map(req -> req.getAssignmentGroup().getTransportation().getId())
                    .collect(Collectors.toSet());

            if (transportSet.contains(transportId)) {
                throw new CustomException("Transport " + transportation.getLicensePlateNumber()
                        + " has been assigned in another request with the same date!", HttpStatus.BAD_REQUEST);
            }

            // Get workers assigned on transport day
            Set<UUID> assignedWorkerIds = requestList.stream()
                    .flatMap(req -> req.getAssignmentGroup().getMembers().stream())
                    .map(member -> member.getMember().getId())
                    .collect(Collectors.toSet());

            // Assign members
            AssignmentGroup assignmentGroup = new AssignmentGroup();
            List<GroupMember> memberList = new ArrayList<>();
            boolean hasDriver = false;

            for (UUID uuid : memberIds) {
                User member = userRepository.findById(uuid)
                        .orElseThrow(() -> new CustomException("Member not found!", HttpStatus.NOT_FOUND));

                if (member.getStatus() == Status.INACTIVE) {
                    throw new CustomException("Member is in INACTIVE state!", HttpStatus.BAD_REQUEST);
                }

                if (member.getRole() != Role.DRIVER && member.getRole() != Role.PORTER) {
                    throw new CustomException("Roles other than Driver and Porter cannot be added!",
                            HttpStatus.BAD_REQUEST);
                }

                if (assignedWorkerIds.contains(member.getId())) {
                    throw new CustomException(member.getRole() + " " + member.getFullName()
                            + " has been assigned in another request with the same date!", HttpStatus.BAD_REQUEST);
                }

                if (member.getRole() == Role.DRIVER) {
                    hasDriver = true;
                }

                memberList.add(new GroupMember(LocalDateTime.now(), false, member, assignmentGroup));
            }

            if (!hasDriver) {
                throw new CustomException("No Driver has been added", HttpStatus.BAD_REQUEST);
            }

            // Set assignment group
            assignmentGroup.setMembers(memberList);
            assignmentGroup.setTransportation(transportation);
            request.setAssignmentGroup(assignmentGroup);
            request.setAdminResponse(adminResponse.isEmpty()
                    ? "Assigned " + memberIds.size() + " member(s) for transportation request"
                    : adminResponse);

            // Save request
            Request savedRequest = requestRepository.save(request);
            RequestResponse requestResponse = modelMapper.map(savedRequest, RequestResponse.class);

            return ResultResponse.success(requestResponse, "Assign request group members successfully!",
                    HttpStatus.OK.value());

        } catch (CustomException e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            return ResultResponse.fail(errorMessages, e.getStatus().value());
        } catch (Exception e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("An unexpected error occurred: " + e.getMessage());
            return ResultResponse.fail(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
