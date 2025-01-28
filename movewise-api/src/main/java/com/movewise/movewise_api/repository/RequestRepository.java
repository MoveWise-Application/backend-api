package com.movewise.movewise_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movewise.movewise_api.entity.Request;

public interface RequestRepository extends JpaRepository<Request, UUID> {
    Optional<Request> findById(UUID id);
}