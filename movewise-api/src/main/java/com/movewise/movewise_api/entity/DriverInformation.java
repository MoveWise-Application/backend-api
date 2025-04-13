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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "\"driver_information\"")
public class DriverInformation extends BaseEntity {

    @Column(name = "driver_license_number", nullable = false)
    private String driverLicenseNumber;

    @Column(name = "driver_license_owner_name", nullable = true)
    private String driverLicenseOwnerName;

    @Column(name = "driver_license_owner_date_of_birth", nullable = true)
    private String driverLicenseOwnerDateOfBirth;

    @Column(name = "driver_license_owner_nationality", nullable = true)
    private String driverLicenseOwnerNationality;

    @Column(name = "driver_license_class", nullable = false)
    private String driverLicenseClass;

    @Column(name = "license_expiry_date", nullable = true)
    private String driverlicenseExpiryDate;

    @Column(name = "driver_license_provided_date", nullable = true)
    private String driverLicenseProvidedDate;

    @Column(name = "citizen_identity_number", nullable = false)
    private String citizenIdentityNumber;

    @Column(name = "citizen_identity_owner_name", nullable = true)
    private String citizenIdentityOwnerName;

    @Column(name = "citizen_identity_owner_sex", nullable = true)
    private String citizenIdentityOwnerSex;

    @Column(name = "citizen_identity_nationality", nullable = true)
    private String citizenIdentityNationality;

    @Column(name = "citizen_identity_expiry_date", nullable = true)
    private String citizenIdentityExpiryDate;

    @Column(name = "citizen_identity_provided_date", nullable = true)
    private String citizenIdentityProvidedDate;

    @Column(name = "citizen_identity_provided_location", nullable = true)
    private String citizenIdentityProvidedLocation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    @JsonIgnore
    private User driver;
}