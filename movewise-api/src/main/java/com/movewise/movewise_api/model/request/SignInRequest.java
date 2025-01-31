package com.movewise.movewise_api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    @NotBlank(message = "The email is required.")
    private String email;

    @NotBlank(message = "The password is required.")
    private String password;
}
