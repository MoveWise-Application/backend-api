package com.movewise.movewise_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.model.response.invoice.InvoiceConfigResponse;
import com.movewise.movewise_api.service.ConfigurationService;

@RestController
@RequestMapping(value = "/api/auth/configurations")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @PostMapping("invoice/update")
    public ResponseEntity<ResultResponse<InvoiceConfigResponse>> updateConfig(@RequestParam int days,
            @RequestParam String description) {
        ResultResponse<InvoiceConfigResponse> resultResponse = configurationService.updateInvoiceConfiguration(days,
                description);
        if (resultResponse.isSuccess()) {
            return ResponseEntity.status(resultResponse.getStatusCode()).body(resultResponse);
        } else {
            return ResponseEntity.status(resultResponse.getStatusCode()).body(resultResponse);
        }
    }

    @GetMapping("invoice/current")
    public ResponseEntity<ResultResponse<InvoiceConfigResponse>> getCurrentConfig() {
        ResultResponse<InvoiceConfigResponse> resultResponse = configurationService.getCurrentInvoiceConfiguration();
        if (resultResponse.isSuccess()) {
            return ResponseEntity.status(resultResponse.getStatusCode()).body(resultResponse);
        } else {
            return ResponseEntity.status(resultResponse.getStatusCode()).body(resultResponse);
        }
    }
}
