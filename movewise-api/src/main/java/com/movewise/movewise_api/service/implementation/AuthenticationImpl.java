package com.movewise.movewise_api.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.movewise.movewise_api.model.request.SignInRequest;
import com.movewise.movewise_api.model.request.SignUpRequest;
import com.movewise.movewise_api.model.response.UserResponse;
import com.movewise.movewise_api.repository.UserRepository;
import com.movewise.movewise_api.service.AuthenticationService;

public class AuthenticationImpl implements AuthenticationService {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponse signUp(SignUpRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signUp'");
    }

    @Override
    public UserResponse signIn(SignInRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signIn'");
    }

}
