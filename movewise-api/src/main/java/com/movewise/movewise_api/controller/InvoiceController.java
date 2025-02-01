package com.movewise.movewise_api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movewise.movewise_api.entity.enumberable.PaymentMethod;
import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.model.response.invoice.InvoiceResponse;
import com.movewise.movewise_api.service.InvoiceService;

@RestController
@RequestMapping(value = "/api/auth/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("request")
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_ADMIN')")
    public ResponseEntity<ResultResponse<InvoiceResponse>> createRequestInvoice(@PathVariable UUID requestId,
            @RequestParam PaymentMethod paymentMethod) {

        ResultResponse<InvoiceResponse> resultResponse = invoiceService.createRequestInvoice(requestId, paymentMethod);

        if (resultResponse.isSuccess()) {
            return ResponseEntity.status(resultResponse.getStatusCode()).body(resultResponse);
        } else {
            return ResponseEntity.status(resultResponse.getStatusCode()).body(resultResponse);
        }
    }
}