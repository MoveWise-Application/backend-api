package com.movewise.movewise_api.Model;

import java.sql.Timestamp;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_birth", nullable = true)
    private Timestamp dateOfBirth;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastAccess", nullable = true)
    private Timestamp lastAccess;

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
}
