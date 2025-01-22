package com.movewise.movewise_api.Model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movewise.movewise_api.Model.Enumberable.Gender;
import com.movewise.movewise_api.Model.Enumberable.Role;
import com.movewise.movewise_api.Model.Enumberable.Status;
import com.movewise.movewise_api.Model.Enumberable.WorkingStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
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
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "avatar", nullable = true)
    private String avatar;

    @Column(name = "phone", length = 10, nullable = true)
    private String phone;

    @Column(name = "date_of_birth", nullable = true)
    private LocalDateTime dateOfBirth;

    @Column(name = "lastAccess", nullable = true)
    private LocalDateTime lastAccess;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Enumerated(EnumType.ORDINAL)
    private WorkingStatus workingStatus;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @OneToOne(mappedBy = "user")
    private BankAccount BankAccount;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<GroupMember> members;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Request> requests;

    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StatusLog> statusLogs;
}
