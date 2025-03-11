package com.movewise.movewise_api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.movewise.movewise_api.entity.User;
import com.movewise.movewise_api.entity.enumberable.Gender;
import com.movewise.movewise_api.entity.enumberable.Role;
import com.movewise.movewise_api.entity.enumberable.Status;
import com.movewise.movewise_api.model.request.authentication.SignUpRequest;
import com.movewise.movewise_api.model.response.user.UserResponse;
import com.movewise.movewise_api.repository.UserRepository;
import com.movewise.movewise_api.service.implementation.AuthenticationImpl;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationImpl authenticationImpl;

    @BeforeEach
    void setUp() {
        System.out.println("First Initialization of Test");
        System.out.flush();
    }

    @Test
    void signUp_ShouldReturnUserResponse_WhenValidRequest() {
        // Arrange
        SignUpRequest request = new SignUpRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123@");
        request.setConfirmedPassword("password123@");
        request.setFullName("Le Quy Diem");
        request.setPhone("0123456789");
        request.setDateOfBirth(LocalDateTime.of(2003, 8, 11, 0, 0));
        request.setGender(Gender.MALE);
        request.setRole(Role.CUSTOMER);

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword("encodedPassword");
        user.setStatus(Status.ACTIVE);
        user.setVerificationCode("randomCode");

        User savedUser = new User();
        savedUser.setEmail(request.getEmail());
        savedUser.setPassword("encodedPassword");
        savedUser.setStatus(Status.ACTIVE);
        savedUser.setVerificationCode("randomCode");

        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setEmail("test@example.com");

        // Mocking the progress of the code
        Mockito.when(modelMapper.map(request, User.class)).thenReturn(user);
        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(user)).thenReturn(savedUser);
        Mockito.when(modelMapper.map(savedUser, UserResponse.class)).thenReturn(expectedResponse);

        // Call the function
        UserResponse actualResponse = authenticationImpl.signUp(request);

        // Assert
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());

        // Verify interactions
        verify(modelMapper).map(request, User.class);
        verify(passwordEncoder).encode(request.getPassword());
        verify(userRepository).save(user);
        verify(modelMapper).map(savedUser, UserResponse.class);
    }
}
