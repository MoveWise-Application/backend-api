package com.movewise.movewise_api.model.response.transportation;

import java.util.List;

import com.movewise.movewise_api.entity.enumberable.TransportationStatus;
import com.movewise.movewise_api.model.response.transportImage.TransportImageResponse;
import com.movewise.movewise_api.model.response.truckType.TruckTypeResponse;

public class TransportationResponse {
    public String description;

    public String licensePlateNumber;

    public String vehicleVerification;

    public String note;

    public TransportationStatus transportationStatus;

    public List<TransportImageResponse> transportImages;

    public TruckTypeResponse truckType;
}
