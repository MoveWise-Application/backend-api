package com.movewise.movewise_api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movewise.movewise_api.entity.User;
import com.movewise.movewise_api.entity.enumberable.Role;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findByVerificationCode(String verificationCode);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findByRole(Role role);
}
