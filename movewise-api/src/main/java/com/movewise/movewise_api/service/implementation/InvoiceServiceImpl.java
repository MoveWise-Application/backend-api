package com.movewise.movewise_api.service.implementation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.movewise.movewise_api.configuration.InvoiceConfiguration;
import com.movewise.movewise_api.entity.Invoice;
import com.movewise.movewise_api.entity.Request;
import com.movewise.movewise_api.entity.enumberable.InvoiceType;
import com.movewise.movewise_api.entity.enumberable.PaymentMethod;
import com.movewise.movewise_api.exception.CustomException;
import com.movewise.movewise_api.model.response.ResultResponse;
import com.movewise.movewise_api.model.response.invoice.InvoiceResponse;
import com.movewise.movewise_api.repository.InvoiceRepository;
import com.movewise.movewise_api.repository.RequestRepository;
import com.movewise.movewise_api.service.InvoiceService;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private InvoiceConfiguration invoiceConfiguration;

    @Override
    public ResultResponse<InvoiceResponse> createRequestInvoice(UUID requestId, PaymentMethod paymentMethod) {
        try {
            Optional<Request> requestOptional = requestRepository.findById(requestId);
            if (requestOptional.isEmpty()) {
                throw new CustomException("Request not found!", HttpStatus.NOT_FOUND);
            }

            Request request = requestOptional.get();

            // Initialize request invoice
            Invoice invoice = new Invoice();

            // Add information
            invoice.setDescription(invoiceConfiguration.getDescription());
            invoice.setNote("");
            invoice.setInvoiceNumber(invoiceConfiguration.generateInvoiceNumber());
            invoice.setDueDate(LocalDateTime.now().plusDays(invoiceConfiguration.getDueDays()));

            // Set price - only the loadingPriceList of the service price is calculated
            invoice.setTotalPrice(request.getRequestedService().getTransportationPriceList().getTruckType()
                    .getLoadingPriceList().getTotalPrice());
            invoice.setInvoiceType(InvoiceType.PENDING);
            BigDecimal defaultDeposit = new BigDecimal("0.00");
            invoice.setDepositPrice(defaultDeposit);

            // Set invoice request
            invoice.setRequestInvoice(request);

            // Save invoice
            Invoice savedInvoice = invoiceRepository.save(invoice);

            InvoiceResponse response = modelMapper.map(savedInvoice, InvoiceResponse.class);
            return ResultResponse.success(response, "", HttpStatus.CREATED.value());

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

    @Override
    public ResultResponse<InvoiceResponse> createOrderInvoice(UUID orderId, PaymentMethod paymentMethod) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createOrderInvoice'");
    }

}
