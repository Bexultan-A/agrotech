package com.example.agrotech.controller;

import com.example.agrotech.model.AchievementEarned;
import com.example.agrotech.model.User;
import com.example.agrotech.service.GamificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gamification")
public class GamificationController {

    @Autowired
    private GamificationService gamificationService;

    /**
     * Add experience points to a user and check for achievements
     */
    @PostMapping("/add-experience/{userId}")
    public String addExperience(@PathVariable long userId, @RequestParam int points) {
        gamificationService.addExperience(userId, points);
        return "Experience added successfully!";
    }

    /**
     * Get all achievements earned by a user
     */
    @GetMapping("/achievements-earned/{userId}")
    public List<AchievementEarned> getUserAchievements(@PathVariable long userId) {
        return gamificationService.getUserAchievements(userId);
    }

    @GetMapping("/leaderboard")
    public List<User> getLeaderboard() {
        return gamificationService.getTopUsers();
    }

    @PostMapping("/task-complete")
    public String completeTask(@PathVariable long userId, @PathVariable long taskAssignedId) {
        return gamificationService.completeTask(userId, taskAssignedId);
    }

    @PostMapping("/task-failed/{userId}/{taskAssignedId}")
    public String failedTask(@PathVariable long userId, @PathVariable long taskAssignedId) {
        return gamificationService.failedTask(userId, taskAssignedId);
    }
}
