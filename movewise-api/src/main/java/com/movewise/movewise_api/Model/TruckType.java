package com.movewise.movewise_api.Model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
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
@Table(name = "transportation")
public class TruckType extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "max_weight", nullable = false, precision = 10, scale = 2)
    private BigDecimal maxWeight;

    @Column(name = "max_length", nullable = false, precision = 10, scale = 2)
    private BigDecimal maxLength;

    @Column(name = "max_width", nullable = false, precision = 10, scale = 2)
    private BigDecimal maxWidth;

    @OneToMany(mappedBy = "truckType", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transportation> transportations;
}
