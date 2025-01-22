package com.movewise.movewise_api.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movewise.movewise_api.Model.Enumberable.RequestStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "request")
public class Request extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private RequestStatus requestStatus;

    @Column(name = "transported_date", nullable = true)
    private LocalDateTime TransportedDate;

    @Column(name = "note")
    private String note;

    @Column(name = "admin_response")
    private String adminResponse;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignment_group_id", referencedColumnName = "id")
    @JsonIgnore
    private AssignmentGroup assignmentGroup;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @JsonIgnore
    private Service requestedService;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Item> items;

    @OneToMany(mappedBy = "requestInvoice", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Invoice> invoices;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "request_customer_info_id", referencedColumnName = "id")
    @JsonIgnore
    private RequestCustomerInfo requestCustomerInfo;

    @OneToOne(mappedBy = "requestOrder")
    private Order order;

    @OneToMany(mappedBy = "requestLog", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StatusLog> requestStatusLogs;
}
