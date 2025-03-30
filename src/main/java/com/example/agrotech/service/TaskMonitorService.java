package com.example.agrotech.service;

import com.example.agrotech.model.TaskAssigned;
import com.example.agrotech.repository.TaskAssignedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskMonitorService {

    @Autowired
    private TaskAssignedRepository taskAssignedRepository;

    @Autowired
    private GamificationService gamificationService;

    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
    public void checkPendingTasks() {
        LocalDateTime now = LocalDateTime.now();

        // Find tasks that are still Active and past their deadline
        List<TaskAssigned> overdueTasks = taskAssignedRepository.findOverdueTasks(now);

        for (TaskAssigned taskAssigned : overdueTasks) {
            gamificationService.failedTask(taskAssigned.getUser().getId(), taskAssigned.getId());
        }
    }
}
