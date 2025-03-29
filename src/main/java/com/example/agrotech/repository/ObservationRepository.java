package com.example.agrotech.repository;

import com.example.agrotech.model.Observation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObservationRepository extends JpaRepository<Observation, Long> {
    List<Observation> findByGardenId(Long gardenId);
}
