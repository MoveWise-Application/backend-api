package com.movewise.movewise_api.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movewise.movewise_api.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, UUID> {
    Optional<Request> findById(UUID id);

    List<Request> findByTransportedDate(LocalDateTime transportedDate);
}