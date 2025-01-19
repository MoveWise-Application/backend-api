package com.movewise.movewise_api.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movewise.movewise_api.Model.Enumberable.TransportationStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Transportation extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "license_plate_number", nullable = false)
    private String licensePlateNumber;

    @Column(name = "vehicle_verification", nullable = false)
    private String vehicleVerification;

    @Column(name = "assigned_job_count", nullable = false)
    private int assignedJobCount;

    @Column(name = "note")
    private String note;

    @Enumerated(EnumType.ORDINAL)
    private TransportationStatus transportationStatus;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GroupMember> members;

    @OneToMany(mappedBy = "transportation", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<TransportImage> transportationImages;

    @ManyToOne
    @JoinColumn(name = "truck_type_id")
    private TruckType truckType;
}
