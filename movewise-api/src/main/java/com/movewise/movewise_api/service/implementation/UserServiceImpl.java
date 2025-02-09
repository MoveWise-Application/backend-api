package com.movewise.movewise_api.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movewise.movewise_api.entity.User;
import com.movewise.movewise_api.exception.CustomException;
import com.movewise.movewise_api.repository.UserRepository;
import com.movewise.movewise_api.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    // change after deployment
    private final static String SITE_URL = "http://localhost:8080";

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
                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new CustomException("User not found.", HttpStatus.NOT_FOUND));
                ;
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

    @Transactional
    @Override
    public boolean verify(String verificationCode) {
        try {
            User user = userRepository.findByVerificationCode(verificationCode)
                    .orElseThrow(() -> new CustomException("User not found.", HttpStatus.NOT_FOUND));

            if (user == null || user.isEnabled()) {
                return false;
            } else {
                user.setVerificationCode(null);
                user.setEnabled(true);

                userRepository.save(user);

                return true;
            }
        } catch (Exception e) {
            throw new CustomException("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
