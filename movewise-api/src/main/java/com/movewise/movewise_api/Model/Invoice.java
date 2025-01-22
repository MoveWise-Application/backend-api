package com.movewise.movewise_api.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.movewise.movewise_api.Model.Enumberable.InvoiceType;
import com.movewise.movewise_api.Model.Enumberable.PaymentMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "invoice")
public class Invoice extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "note")
    private String note;

    @Column(name = "invoice_number", nullable = false)
    private String invoiceNumber;

    @Column(name = "due_date", nullable = true)
    private LocalDateTime dueDate;

    @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.ORDINAL)
    private InvoiceType invoiceType;

    @Column(name = "deposit_price", precision = 10, scale = 2, nullable = false)
    private BigDecimal depositPrice;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request requestInvoice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order orderInvoice;
}
