package com.movewise.movewise_api.service;

import com.movewise.movewise_api.entity.User;

public interface UserService {
    User findUserByJwt();

    boolean verify(String verificationCode);
}
