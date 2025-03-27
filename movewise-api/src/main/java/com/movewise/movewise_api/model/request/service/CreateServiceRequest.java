package com.movewise.movewise_api.model.request.service;

import java.util.List;
import java.util.UUID;

import com.movewise.movewise_api.entity.enumberable.Status;

public class CreateServiceRequest {

    public String name;

    public String description;

    public String version;

    public Status status;

    public List<UUID> packagingPriceListIds;

    public UUID transportationPriceListId;

    public UUID parentServiceId;
}
