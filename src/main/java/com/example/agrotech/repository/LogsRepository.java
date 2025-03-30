package com.example.agrotech.repository;

import com.example.agrotech.model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LogsRepository extends JpaRepository<Logs, Long> {
    List<Logs> findByUserId(Long userId);
}
