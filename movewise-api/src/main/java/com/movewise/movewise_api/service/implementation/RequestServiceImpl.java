package com.movewise.movewise_api.service.implementation;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.movewise.movewise_api.entity.Item;
import com.movewise.movewise_api.entity.Request;
import com.movewise.movewise_api.entity.User;
import com.movewise.movewise_api.entity.enumberable.RequestStatus;
import com.movewise.movewise_api.exception.CustomException;
import com.movewise.movewise_api.model.request.RequestRequest;
import com.movewise.movewise_api.model.response.RequestResponse;
import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.repository.ItemRepository;
import com.movewise.movewise_api.repository.RequestRepository;
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

            // Set neccessary information
            request.setRequestStatus(status);
            request.setAdminResponse(adminResponse);

            // Save request
            Request savedRequest = requestRepository.save(request);

            RequestResponse requestResponse = modelMapper.map(savedRequest, RequestResponse.class);
            return ResultResponse.success(requestResponse,
                    "Update request status successfully!",
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
}
