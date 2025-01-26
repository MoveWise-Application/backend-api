package com.movewise.movewise_api.service;

import com.movewise.movewise_api.model.request.RequestRequest;
import com.movewise.movewise_api.model.response.RequestResponse;

public interface RequestService {
    RequestResponse createRequest(RequestRequest request);
}
