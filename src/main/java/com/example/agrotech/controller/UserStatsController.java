package com.example.agrotech.controller;

import com.example.agrotech.model.UserStats;
import com.example.agrotech.service.UserStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user-stats")
@RequiredArgsConstructor
public class UserStatsController {

    private final UserStatsService userStatsService;

    @GetMapping("/{id}")
    public ResponseEntity<UserStats> getUserStatsById(@PathVariable Long id) {
        Optional<UserStats> userStats = userStatsService.getUserStatsById(id);
        return userStats.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserStats> getUserStatsByUser(@PathVariable Long userId) {
        Optional<UserStats> userStats = userStatsService.getUserStatsByUserId(userId);
        return userStats.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserStats createUserStats(@RequestBody UserStats userStats) {
        return userStatsService.saveUserStats(userStats);
    }

    @PutMapping("/update-experience/{userId}")
    public ResponseEntity<Void> updateUserExperience(@PathVariable Long userId, @RequestParam int points) {
        userStatsService.updateUserExperience(userId, points);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserStats(@PathVariable Long id) {
        userStatsService.deleteUserStats(id);
        return ResponseEntity.noContent().build();
    }
}
