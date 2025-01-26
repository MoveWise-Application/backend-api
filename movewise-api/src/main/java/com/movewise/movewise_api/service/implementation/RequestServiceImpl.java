package com.movewise.movewise_api.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.movewise.movewise_api.exception.CustomException;
import com.movewise.movewise_api.model.request.RequestRequest;
import com.movewise.movewise_api.model.response.RequestResponse;
import com.movewise.movewise_api.repository.RequestRepository;
import com.movewise.movewise_api.service.RequestService;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RequestResponse createRequest(RequestRequest request) {
        try {
            RequestResponse requestResponse = new RequestResponse();
            return requestResponse;
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Cannot convert to response!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
