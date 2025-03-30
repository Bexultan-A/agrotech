package com.example.agrotech.repository;

import com.example.agrotech.model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LogsRepository extends JpaRepository<Logs, Long> {
    @Query("SELECT l FROM Logs l WHERE l.garden.owner.id = :userId")
    List<Logs> findByUserId(@Param("userId") Long userId);
}
