package com.movewise.movewise_api.service;

import com.movewise.movewise_api.model.request.SignInRequest;
import com.movewise.movewise_api.model.request.SignUpRequest;
import com.movewise.movewise_api.model.response.UserResponse;

public interface AuthenticationService {
    UserResponse signUp(SignUpRequest request);

    UserResponse signIn(SignInRequest request);
}
