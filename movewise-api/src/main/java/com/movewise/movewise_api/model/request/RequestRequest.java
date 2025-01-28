package com.movewise.movewise_api.model.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class RequestRequest {

    public String description;

    public LocalDateTime TransportedDate;

    public String note;

    public String adminResponse;

    public BigDecimal totalPrice;

    public UUID customerId;

    public UUID serviceId;

    public List<UUID> itemIds;

    public RequestCustomerInfoRequest requestCustomerInfo;
}
