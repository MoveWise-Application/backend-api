package com.movewise.movewise_api.entity;

import java.util.List;

import com.movewise.movewise_api.entity.enumberable.Status;

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
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "service")
public class Service extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "version", nullable = false)
    private String version;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "package_price_list_id")
    private PackagingPriceList packagingPriceList;

    @OneToOne(mappedBy = "service")
    private TransportationPriceList transportationPriceList;

    @OneToMany(mappedBy = "requestedService", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Request> requests;

    @ManyToOne
    @JoinColumn(name = "parent_service_id")
    private Service parentService;
}
