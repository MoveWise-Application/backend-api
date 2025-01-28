package com.movewise.movewise_api.service;

import java.util.UUID;

import com.movewise.movewise_api.entity.enumberable.RequestStatus;
import com.movewise.movewise_api.model.request.RequestRequest;
import com.movewise.movewise_api.model.response.RequestResponse;
import com.movewise.movewise_api.model.response.ResultResponse;

public interface RequestService {
    ResultResponse<RequestResponse> createRequest(RequestRequest request);

    ResultResponse<RequestResponse> updateRequestStatus(UUID requestId, RequestStatus status, String adminResponse);
}
