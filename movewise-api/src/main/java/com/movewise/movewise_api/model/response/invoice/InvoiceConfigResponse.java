package com.movewise.movewise_api.model.response.invoice;

public class InvoiceConfigResponse {

    public String description;

    public int dueDuration;

    public InvoiceConfigResponse(int dueDuration, String description) {
        this.dueDuration = dueDuration;
        this.description = description;
    }
}