package com.movewise.movewise_api.service;

import com.movewise.movewise_api.entity.User;
import com.movewise.movewise_api.model.response.user.UserResponse;

public interface UserService {
    User findUserByJwt();

    boolean verify(String verificationCode);

    UserResponse findUserByEmail(String email);
}
