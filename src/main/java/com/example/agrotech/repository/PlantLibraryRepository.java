package com.example.agrotech.repository;

import com.example.agrotech.model.PlantLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantLibraryRepository extends JpaRepository<PlantLibrary, Long> {
    Optional<PlantLibrary> findByName(String name);
}
