package com.movewise.movewise_api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movewise.movewise_api.entity.AssignmentGroup;

@Repository
public interface AssignmentGroupRepository extends JpaRepository<AssignmentGroup, UUID> {

}
