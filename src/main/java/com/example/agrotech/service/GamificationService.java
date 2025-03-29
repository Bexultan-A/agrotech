package com.example.agrotech.service;

import com.example.agrotech.model.*;
import com.example.agrotech.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GamificationService {

    @Autowired
    private UserStatsRepository userStatsRepository;

    @Autowired
    private AchievementRepository achievementRepository;

    @Autowired
    private AchievementEarnedRepository achievementEarnedRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public String completeTask(long userId, long taskId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Task> taskOptional = taskRepository.findById(taskId);

        if (userOptional.isPresent() && taskOptional.isPresent()) {
            Task task = taskOptional.get();

            int xp = task.getExperiencePoints();
            addExperience(userId, xp);

            return "Task completed! Earned " + xp + " XP.";
        }
        return "User or task not found!";
    }

    public void addExperience(long userId, int points) {
        UserStats stats = userStatsRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User stats not found"));

        stats.setExperiencePoints(stats.getExperiencePoints() + points);

        // Level up check
        if (stats.getExperiencePoints() >= getNextLevelThreshold(stats.getLevel())) {
            stats.setLevel(stats.getLevel() + 1);
            System.out.println("User leveled up! New level: " + stats.getLevel());
        }

        userStatsRepository.save(stats);

        // Check for achievements
        checkAchievements(userId, stats.getExperiencePoints());
    }

    private int getNextLevelThreshold(int level) {
        return level * 100; // Example: Need 100 * level points to level up
    }

    private void checkAchievements(long userId, int currentPoints) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Achievement> allAchievements = achievementRepository.findAll();

        for (Achievement achievement : allAchievements) {
            // Check if user already earned this achievement
            Optional<AchievementEarned> existingAchievement = achievementEarnedRepository
                    .findByUserAndAchievement(user, achievement);

            if (currentPoints >= achievement.getPointsRequired() && existingAchievement.isEmpty()) {
                // Grant achievement
                AchievementEarned newAchievement = new AchievementEarned();
                newAchievement.setUser(user);
                newAchievement.setAchievement(achievement);
                newAchievement.setEarnedAt(LocalDateTime.now());

                achievementEarnedRepository.save(newAchievement);
                System.out.println("New achievement unlocked: " + achievement.getName());
            }
        }
    }

    public List<AchievementEarned> getUserAchievements(long userId) {
        return achievementEarnedRepository.findByUserId(userId);
    }
}
