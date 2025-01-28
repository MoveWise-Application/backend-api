package com.movewise.movewise_api.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.movewise.movewise_api.entity.User;
import com.movewise.movewise_api.exception.CustomException;
import com.movewise.movewise_api.repository.UserRepository;
import com.movewise.movewise_api.service.UserService;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User findUserByJwt() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null
                    && authentication.getPrincipal() != null
                    && authentication.getPrincipal() instanceof User) {
                UserDetails userDetails = (User) authentication.getPrincipal();
                String email = userDetails.getUsername();
                User user = userRepository.findByEmail(email);
                return user;
            } else {
                throw new CustomException("Cannot recieve JWT token!", HttpStatus.BAD_REQUEST);
            }
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException("Cannot get user info from JWT token!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
