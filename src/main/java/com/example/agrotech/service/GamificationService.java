package com.example.agrotech.service;

import com.example.agrotech.model.*;
import com.example.agrotech.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private TaskAssignedRepository taskAssignedRepository;

    public List<User> getTopUsers() {
        return userRepository.findTopUsers(PageRequest.of(0, 10));
    }

    public String completeTask(long userId, long taskAssignedId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<TaskAssigned> taskAssignedOPT = taskAssignedRepository.findById(taskAssignedId);

        if (userOptional.isPresent() && taskAssignedOPT.isPresent()) {
            TaskAssigned taskAssigned = taskAssignedOPT.get();

            int xp = taskAssigned.getTask().getExperiencePoints();
            addExperience(userId, xp);

            taskAssigned.getGarden().setCareLevel(taskAssigned.getGarden().getCareLevel() + taskAssigned.getTask().getCareChange());

            taskAssigned.setStatus("Completed");
            taskAssigned.setStatusChangedAt(LocalDateTime.now());
            taskAssignedRepository.save(taskAssigned);

            return "Task completed! Earned " + xp + " XP.";
        }
        return "User or task not found!";
    }

    public String failedTask(long userId, long taskAssignedId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<TaskAssigned> taskAssignedOPT = taskAssignedRepository.findById(taskAssignedId);

        if (userOptional.isPresent() && taskAssignedOPT.isPresent()) {
            TaskAssigned taskAssigned = taskAssignedOPT.get();

            taskAssigned.getGarden().setHealthPoints(taskAssigned.getGarden().getHealthPoints() + taskAssigned.getTask().getHpChange());

            taskAssigned.setStatus("Failed");
            taskAssigned.setStatusChangedAt(LocalDateTime.now());
            taskAssignedRepository.save(taskAssigned);

            return "Task Failed!!!";
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
            int level = stats.getLevel();
            if (level >= 10 && level <= 19 ) {
                stats.setTitle("Amateur");
            } else if (level >= 20 && level <= 29) {
                stats.setTitle("Plant Cooker");
            } else if (level >= 30 && level <= 39) {
                stats.setTitle("Garden White");
            } else if (level >= 40 && level <= 49) {
                stats.setTitle("Platinum Green");
            } else if (level >= 50 && level <= 59) {
                stats.setTitle("Agro Master");
            } else if (level >= 60 && level <= 69) {
                stats.setTitle("Green Adventurer");
            } else if (level >= 1000 && level <= 1009) {
                stats.setTitle("Archimage");
            }
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
