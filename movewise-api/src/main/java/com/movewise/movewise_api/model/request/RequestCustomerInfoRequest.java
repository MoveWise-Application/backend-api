package com.movewise.movewise_api.model.request;

import java.time.LocalDateTime;

import com.movewise.movewise_api.entity.enumberable.Gender;

public class RequestCustomerInfoRequest {

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
