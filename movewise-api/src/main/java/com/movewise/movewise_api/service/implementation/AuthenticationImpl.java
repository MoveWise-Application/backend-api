package com.movewise.movewise_api.service.implementation;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movewise.movewise_api.entity.User;
import com.movewise.movewise_api.entity.enumberable.Status;
import com.movewise.movewise_api.exception.CustomException;
import com.movewise.movewise_api.model.request.authentication.SignInRequest;
import com.movewise.movewise_api.model.request.authentication.SignUpRequest;
import com.movewise.movewise_api.repository.UserRepository;
import com.movewise.movewise_api.service.AuthenticationService;
import com.movewise.movewise_api.service.JwtService;
import com.movewise.movewise_api.model.response.authentication.AuthenticationResponse;
import com.movewise.movewise_api.model.response.user.UserResponse;

@Service
public class AuthenticationImpl implements AuthenticationService {
    private final UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

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
    @Transactional
    public UserResponse signUp(SignUpRequest request) {
        try {
            String randomCode = RandomString.make(64);

            User user = modelMapper.map(request, User.class);

            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setStatus(Status.ACTIVE);
            user.setVerificationCode(randomCode);

            var response = modelMapper.map(userRepository.save(user), UserResponse.class);

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new CustomException("Invalid email or password.", HttpStatus.UNAUTHORIZED));

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()));

            // Check if the user account is enabled
            if (!user.isEnabled()) {
                throw new DisabledException("This account is not activated. Please verify your email.");
            }

            if (user.getStatus() != Status.INACTIVE) {
                var token = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                        .token(token)
                        .build();
            } else {
                throw new CustomException(
                        "This account is blocked. Please contact the administrator for more information.",
                        HttpStatus.BAD_REQUEST);
            }
        } catch (BadCredentialsException e) {
            throw new CustomException("Invalid email or password.", HttpStatus.UNAUTHORIZED);
        } catch (DisabledException e) {
            // Send verification email if the account is disabled
            // userService.sendVerificationEmail(user);
            throw new CustomException(
                    "This account is not activated. Please verify your email.",
                    HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
