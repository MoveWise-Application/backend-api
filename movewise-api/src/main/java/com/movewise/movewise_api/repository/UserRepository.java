package com.movewise.movewise_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movewise.movewise_api.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
