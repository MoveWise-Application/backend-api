package com.movewise.movewise_api.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.movewise.movewise_api.entity.User;
import com.movewise.movewise_api.model.response.user.UserResponse;
import com.movewise.movewise_api.repository.UserRepository;
import com.movewise.movewise_api.service.implementation.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        System.out.println("First Initialization of Test");
    }

    @Test
    void findByEmail_ShouldReturnUserResponse() {
        // 1. create mock data
        String testEmail = "quydiem2015@gmail.com";
        User mockUser = new User();
        mockUser.setEmail(testEmail);

        UserResponse mockUserResponse = new UserResponse();
        mockUserResponse.setEmail(testEmail);

        // 2. define behavior of Repository
        when(userRepository.findByEmail(testEmail)).thenReturn(Optional.of(mockUser));
        when(modelMapper.map(mockUser, UserResponse.class)).thenReturn(mockUserResponse);

        // 3. call service method
        UserResponse actualUser = userServiceImpl.findUserByEmail(testEmail);

        // 4. assert the result
        assertNotNull(actualUser);
        assertEquals(testEmail, actualUser.getEmail());

        // 4.1 ensure repository is called
        verify(userRepository).findByEmail(testEmail);
    }
}
