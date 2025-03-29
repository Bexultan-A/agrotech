package com.example.agrotech.repository;

import com.example.agrotech.model.Garden;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GardenRepository extends JpaRepository<Garden, Long> {
    List<Garden> findByOwnerId(Long ownerId);
}
