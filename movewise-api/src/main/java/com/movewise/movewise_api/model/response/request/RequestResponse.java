package com.movewise.movewise_api.model.response.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.movewise.movewise_api.model.response.item.ItemRequestResponse;
import com.movewise.movewise_api.model.response.requestCustomerInfo.RequestCustomerInfoResponse;
import com.movewise.movewise_api.model.response.service.ServiceResponse;
import com.movewise.movewise_api.model.response.user.CustomerRequestResponse;

public class RequestResponse {

    public UUID id;

    public LocalDateTime createdDate;

    public String description;

    public LocalDateTime TransportedDate;

    public String note;

    public String adminResponse;

    public BigDecimal totalPrice;

    public CustomerRequestResponse customer;

    public ServiceResponse requestedService;

    public List<ItemRequestResponse> items;

    public RequestCustomerInfoResponse requestCustomerInfo;
}
