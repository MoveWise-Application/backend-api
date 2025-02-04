package com.movewise.movewise_api.model.response.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ServiceResponse {

    public UUID id;

    public LocalDateTime createdDate;

    public String description;

    public BigDecimal totalPrice;
}
