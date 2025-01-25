package com.movewise.movewise_api.entity;

import java.math.BigDecimal;
import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movewise.movewise_api.entity.converter.DurationAttributeConverter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@Table(name = "transportation_price_list")
public class TransportationPriceList extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "first_distance", nullable = false, precision = 10, scale = 2)
    private BigDecimal firstDistance;

    @Column(name = "second_distance", nullable = false, precision = 10, scale = 2)
    private BigDecimal secondDistance;

    @Column(name = "third_distance", nullable = false, precision = 10, scale = 2)
    private BigDecimal thirdDistance;

    @Column(name = "waiting_time", nullable = false)
    @Convert(converter = DurationAttributeConverter.class)
    private Duration waitingTime;

    @Column(name = "overnight_extra_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal overnightExtraPrice;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @JsonIgnore
    private Service service;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_type_id", referencedColumnName = "id")
    @JsonIgnore
    private TruckType truckType;
}
