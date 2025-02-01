package com.movewise.movewise_api.service;

import java.util.UUID;

import com.movewise.movewise_api.entity.enumberable.RequestStatus;
import com.movewise.movewise_api.model.request.request.RequestRequest;
import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.model.response.request.RequestResponse;

public interface RequestService {
    ResultResponse<RequestResponse> createRequest(RequestRequest request);

    ResultResponse<RequestResponse> updateRequestStatus(UUID requestId, RequestStatus status, String adminResponse);
}
