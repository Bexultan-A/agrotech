package com.example.agrotech.repository;

import com.example.agrotech.model.TaskAssigned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskAssignedRepository extends JpaRepository<TaskAssigned, Long> {
    @Query("SELECT t FROM TaskAssigned t " +
            "WHERE t.status = 'Active' " +
            "AND t.assignedAt <= :now " +
            "AND FUNCTION('TIMESTAMPADD', MINUTE, t.task.timeToComplete, t.assignedAt) <= :now")
    List<TaskAssigned> findOverdueTasks(@Param("now") LocalDateTime now);

}
