package com.movewise.movewise_api.service;

import java.util.UUID;

import com.movewise.movewise_api.entity.enumberable.PaymentMethod;
import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.model.response.invoice.InvoiceResponse;

public interface InvoiceService {
    ResultResponse<InvoiceResponse> createRequestInvoice(UUID requestId, PaymentMethod paymentMethod);

    ResultResponse<InvoiceResponse> createOrderInvoice(UUID orderId, PaymentMethod paymentMethod);
}