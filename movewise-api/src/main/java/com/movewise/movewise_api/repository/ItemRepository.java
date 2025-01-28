package com.movewise.movewise_api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movewise.movewise_api.entity.Item;

public interface ItemRepository extends JpaRepository<Item, UUID> {
    List<Item> findAllByIdIn(List<UUID> ids);
}