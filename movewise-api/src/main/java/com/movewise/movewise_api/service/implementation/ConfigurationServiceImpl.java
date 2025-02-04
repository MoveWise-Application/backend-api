package com.movewise.movewise_api.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.movewise.movewise_api.configuration.InvoiceConfiguration;
import com.movewise.movewise_api.exception.CustomException;
import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.model.response.invoice.InvoiceConfigResponse;
import com.movewise.movewise_api.service.ConfigurationService;

@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private InvoiceConfiguration invoiceConfig;

    public ResultResponse<InvoiceConfigResponse> updateInvoiceConfiguration(int days, String description) {
        try {
            // Implement the changes
            invoiceConfig.setDueDays(days);
            invoiceConfig.setDescription(description);

            // Convert to response
            InvoiceConfigResponse response = new InvoiceConfigResponse(days, description);
            return ResultResponse.success(response, "Update invoice configuration successfully!",
                    HttpStatus.OK.value());
        } catch (CustomException e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            return ResultResponse.fail(errorMessages, e.getStatus().value());
        } catch (Exception e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("An unexpected error occurred: " + e.getMessage());
            return ResultResponse.fail(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }

    public ResultResponse<InvoiceConfigResponse> getCurrentInvoiceConfiguration() {
        try {
            // Fetch current configuration values
            int dueDays = invoiceConfig.getDueDays();
            String description = invoiceConfig.getDescription();

            // Convert to response
            InvoiceConfigResponse response = new InvoiceConfigResponse(
                    dueDays,
                    description);

            return ResultResponse.success(response, "Invoice Configuration fetched!", HttpStatus.OK.value());
        } catch (CustomException e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            return ResultResponse.fail(errorMessages, e.getStatus().value());
        } catch (Exception e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add("An unexpected error occurred: " + e.getMessage());
            return ResultResponse.fail(errorMessages, HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
    }
}
