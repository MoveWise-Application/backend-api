package com.movewise.movewise_api.Model;

import java.time.LocalDateTime;

import com.movewise.movewise_api.Model.Enumberable.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "service")
public class RequestCustomerInfo extends BaseEntity {

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "avatar", nullable = true)
    private String avatar;

    @Column(name = "phone", length = 10, nullable = true)
    private String phone;

    @Column(name = "date_of_birth", nullable = true)
    private LocalDateTime dateOfBirth;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name = "fetch_address", length = 10, nullable = false)
    private String fetchAddress;

    @Column(name = "fetch_city", length = 10, nullable = false)
    private String fetchCity;

    @Column(name = "deliver_address", length = 10, nullable = false)
    private String deliverAddress;

    @Column(name = "deliver_city", length = 10, nullable = false)
    private String deliverCity;

    @OneToOne(mappedBy = "requestCustomerInfo")
    private Request request;
}
