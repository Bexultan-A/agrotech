package com.example.agrotech.controller;

import com.example.agrotech.model.AchievementEarned;
import com.example.agrotech.service.AchievementEarnedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/achievements-earned")
@RequiredArgsConstructor
public class AchievementEarnedController {

    private final AchievementEarnedService achievementEarnedService;

    @GetMapping
    public List<AchievementEarned> getAllAchievementsEarned() {
        return achievementEarnedService.getAllAchievementsEarned();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchievementEarned> getAchievementEarnedById(@PathVariable Long id) {
        Optional<AchievementEarned> achievementEarned = achievementEarnedService.getAchievementEarnedById(id);
        return achievementEarned.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<AchievementEarned> getAchievementsEarnedByUser(@PathVariable Long userId) {
        return achievementEarnedService.getAchievementsEarnedByUserId(userId);
    }

    @PostMapping
    public AchievementEarned createAchievementEarned(@RequestBody AchievementEarned achievementEarned) {
        return achievementEarnedService.saveAchievementEarned(achievementEarned);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievementEarned(@PathVariable Long id) {
        achievementEarnedService.deleteAchievementEarned(id);
        return ResponseEntity.noContent().build();
    }
}
