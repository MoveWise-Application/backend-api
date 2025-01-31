package com.movewise.movewise_api.model.request;

import java.time.LocalDateTime;

import com.movewise.movewise_api.entity.enumberable.Gender;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    @NotBlank(message = "The email is required.")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "The email is invalid.")
    private String email;

    @NotBlank(message = "The password is required.")
    private String password;

    @NotBlank(message = "The confirmed password is required.")
    private String confirmedPassword;

    @NotBlank(message = "The full name is required.")
    private String fullName;

    @NotBlank(message = "The phone is required.")
    private String phone;

    @NotNull(message = "The date of birth is required.")
    private LocalDateTime dateOfBirth;

    @NotNull(message = "The gender is required.")
    private Gender gender;
}
