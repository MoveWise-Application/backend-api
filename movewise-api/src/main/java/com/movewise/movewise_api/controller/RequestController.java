package com.movewise.movewise_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movewise.movewise_api.entity.enumberable.RequestStatus;
import com.movewise.movewise_api.model.request.request.RequestRequest;
import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.model.response.request.RequestResponse;
import com.movewise.movewise_api.service.RequestService;

@RestController
@RequestMapping(value = "/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<ResultResponse<RequestResponse>> createRequest(@RequestBody RequestRequest request) {
        ResultResponse<RequestResponse> resultResponse = requestService.createRequest(request);

        return ResponseEntity.status(resultResponse.getStatusCode()).body(resultResponse);
    }

    @PutMapping("{requestId}/status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResultResponse<RequestResponse>> updateRequestStatus(
            @PathVariable UUID requestId,
            @RequestParam RequestStatus status,
            @RequestParam String adminResponse) {

        ResultResponse<RequestResponse> result = requestService.updateRequestStatus(requestId, status,
                adminResponse);
        return ResponseEntity.status(result.getStatusCode()).body(result);
    }

    @PutMapping("{requestId}/assign")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResultResponse<RequestResponse>> assignAssignmentGroup(
            @PathVariable UUID requestId,
            @RequestParam String adminResponse,
            @RequestParam UUID transportId,
            @RequestBody List<UUID> memberIds) {

        ResultResponse<RequestResponse> result = requestService.assignAssignmentGroup(requestId, adminResponse,
                memberIds, transportId);

        return ResponseEntity.status(result.getStatusCode()).body(result);
    }
}
