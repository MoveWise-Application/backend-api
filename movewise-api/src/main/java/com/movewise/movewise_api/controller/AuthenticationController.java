package com.movewise.movewise_api.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movewise.movewise_api.model.request.authentication.SignInRequest;
import com.movewise.movewise_api.model.request.authentication.SignUpRequest;
import com.movewise.movewise_api.model.response.UserResponse;
import com.movewise.movewise_api.model.response.authentication.AuthenticationResponse;
import com.movewise.movewise_api.service.AuthenticationService;

import jakarta.mail.MessagingException;

@RequestMapping("/api/")
@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@RequestBody SignUpRequest request) {
        UserResponse response = authenticationService.signUp(request);

        return new ResponseEntity<UserResponse>(response, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody SignInRequest request)
            throws UnsupportedEncodingException, MessagingException {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
