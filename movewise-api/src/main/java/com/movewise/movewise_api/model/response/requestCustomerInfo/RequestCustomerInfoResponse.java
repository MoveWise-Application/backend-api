package com.movewise.movewise_api.model.response.requestCustomerInfo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.movewise.movewise_api.entity.enumberable.Gender;

public class RequestCustomerInfoResponse {

    public UUID id;

    public LocalDateTime createdDate;

    public String email;

    public String fullName;

    public String avatar;

    public String phone;

    public LocalDateTime dateOfBirth;

    public Gender gender;

    public String fetchAddress;

    public String fetchCity;

    public String deliverAddress;

    public String deliverCity;
}
