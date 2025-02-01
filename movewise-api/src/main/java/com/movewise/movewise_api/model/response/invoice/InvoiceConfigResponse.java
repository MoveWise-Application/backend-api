package com.movewise.movewise_api.model.response.invoice;

public class InvoiceConfigResponse {

    public String description;

    public int dueDate;

    public InvoiceConfigResponse(int dueDate, String description) {
        this.dueDate = dueDate;
        this.description = description;
    }
}