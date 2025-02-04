package com.movewise.movewise_api.controller;

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
@RequestMapping(value = "/api/auth/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<ResultResponse<RequestResponse>> createRequest(@RequestBody RequestRequest request) {
        ResultResponse<RequestResponse> resultResponse = requestService.createRequest(request);

        if (resultResponse.isSuccess()) {
            return ResponseEntity.status(resultResponse.getStatusCode()).body(resultResponse);
        } else {
            return ResponseEntity.status(resultResponse.getStatusCode()).body(resultResponse);
        }
    }

    @PutMapping("{requestId}/status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResultResponse<RequestResponse>> updateRequestStatus(
            @PathVariable UUID requestId,
            @RequestParam RequestStatus status,
            @RequestParam String adminResponse) {

        ResultResponse<RequestResponse> result = requestService.updateRequestStatus(requestId, status,
                adminResponse);
        if (result.isSuccess()) {
            return ResponseEntity.status(result.getStatusCode()).body(result);
        } else {
            return ResponseEntity.status(result.getStatusCode()).body(result);
        }
    }
}
