package com.movewise.movewise_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "loading_price_list")
public class LoadingPriceList extends BaseEntity {

    @Column(name = "loading_labor", nullable = false)
    private int loadingLabor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_type_id", referencedColumnName = "id")
    @JsonIgnore
    private TruckType loadingTruckType;
}