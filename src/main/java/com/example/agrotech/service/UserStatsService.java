package com.example.agrotech.service;

import com.example.agrotech.model.UserStats;
import com.example.agrotech.repository.UserStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserStatsService {

    private final UserStatsRepository userStatsRepository;

    public Optional<UserStats> getUserStatsById(Long id) {
        return userStatsRepository.findById(id);
    }

    public Optional<UserStats> getUserStatsByUserId(Long userId) {
        return userStatsRepository.findByUserId(userId);
    }

    public UserStats saveUserStats(UserStats userStats) {
        return userStatsRepository.save(userStats);
    }

    public void deleteUserStats(Long id) {
        userStatsRepository.deleteById(id);
    }

    public void updateUserExperience(Long userId, int points) {
        Optional<UserStats> userStatsOpt = userStatsRepository.findByUserId(userId);
        if (userStatsOpt.isPresent()) {
            UserStats userStats = userStatsOpt.get();
            userStats.setExperiencePoints(userStats.getExperiencePoints() + points);

            // Level-up logic
            if (userStats.getExperiencePoints() >= getExperienceThreshold(userStats.getLevel())) {
                userStats.setLevel(userStats.getLevel() + 1);
            }

            userStatsRepository.save(userStats);
        }
    }

    private int getExperienceThreshold(int level) {
        return 100 * level; // Example threshold formula
    }
}
