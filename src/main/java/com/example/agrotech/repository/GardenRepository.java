package com.example.agrotech.repository;

import com.example.agrotech.model.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GardenRepository extends JpaRepository<Garden, Long> {
    List<Garden> findByOwnerId(Long ownerId);
    long countByOwnerIdAndStatus(long userId, String status);
    long countByOwnerId(long userId);

    @Query("SELECT g FROM Garden g WHERE g.owner.id = :userId AND g.status = 'Harvested' AND g.harvestDate IS NOT NULL AND g.sowingDate IS NOT NULL")
    List<Garden> findHarvestedGardensWithDates(@Param("userId") long userId);
}
