package com.movewise.movewise_api.Model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movewise.movewise_api.Model.Enumberable.Gender;
import com.movewise.movewise_api.Model.Enumberable.Provider;
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
@Table(name = "\"user\"")
public class User extends BaseEntity implements UserDetails {

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

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Enumerated(EnumType.ORDINAL)
    private WorkingStatus workingStatus;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Enumerated(EnumType.ORDINAL)
    private Provider provider;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleNameWithPrefix = "ROLE_" + role.name();
        return List.of(new SimpleGrantedAuthority(roleNameWithPrefix));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
