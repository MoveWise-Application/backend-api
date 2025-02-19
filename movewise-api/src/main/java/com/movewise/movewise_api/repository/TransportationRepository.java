package com.movewise.movewise_api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movewise.movewise_api.entity.Transportation;

@Repository
public interface TransportationRepository extends JpaRepository<Transportation, UUID> {
    Optional<Transportation> findById(UUID id);
}
