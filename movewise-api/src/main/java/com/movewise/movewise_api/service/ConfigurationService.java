package com.movewise.movewise_api.service;

import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.model.response.invoice.InvoiceConfigResponse;

public interface ConfigurationService {
    ResultResponse<InvoiceConfigResponse> updateInvoiceConfiguration(int days, String description);

    ResultResponse<InvoiceConfigResponse> getCurrentInvoiceConfiguration();
}
