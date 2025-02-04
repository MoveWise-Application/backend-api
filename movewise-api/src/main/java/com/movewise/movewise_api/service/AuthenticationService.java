package com.movewise.movewise_api.service;

import com.movewise.movewise_api.model.request.authentication.SignInRequest;
import com.movewise.movewise_api.model.request.authentication.SignUpRequest;
import com.movewise.movewise_api.model.response.UserResponse;
import com.movewise.movewise_api.model.response.authentication.AuthenticationResponse;

public interface AuthenticationService {
    UserResponse signUp(SignUpRequest request);

    AuthenticationResponse signIn(SignInRequest request);
}
