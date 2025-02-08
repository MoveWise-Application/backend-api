package com.movewise.movewise_api.model.response.user;

import org.springframework.lang.Nullable;

import com.movewise.movewise_api.entity.enumberable.Gender;
import com.movewise.movewise_api.entity.enumberable.Role;
import com.movewise.movewise_api.entity.enumberable.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String email;
    private String fullName;
    private String phone;
    private String dateOfBirth;
    private @Nullable String avatar;
    private Gender gender;
    private Status status;
    private Role role;
}
