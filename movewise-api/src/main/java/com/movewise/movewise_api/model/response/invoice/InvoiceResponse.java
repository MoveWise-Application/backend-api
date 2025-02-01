package com.movewise.movewise_api.model.response.invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.movewise.movewise_api.entity.enumberable.InvoiceType;
import com.movewise.movewise_api.entity.enumberable.PaymentMethod;
import com.movewise.movewise_api.model.response.order.OrderResponse;
import com.movewise.movewise_api.model.response.request.RequestResponse;

public class InvoiceResponse {

    public UUID id;

    public LocalDateTime createdDate;

    public String description;

    public String note;

    public String invoiceNumber;

    public LocalDateTime dueDate;

    public BigDecimal totalPrice;

    public PaymentMethod paymentMethod;

    public InvoiceType invoiceType;

    public BigDecimal depositPrice;

    public RequestResponse requestInvoice;

    public OrderResponse orderInvoice;
}
