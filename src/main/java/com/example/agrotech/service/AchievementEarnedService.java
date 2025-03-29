package com.example.agrotech.service;

import com.example.agrotech.model.AchievementEarned;
import com.example.agrotech.repository.AchievementEarnedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AchievementEarnedService {

    private final AchievementEarnedRepository achievementEarnedRepository;

    public List<AchievementEarned> getAllAchievementsEarned() {
        return achievementEarnedRepository.findAll();
    }

    public Optional<AchievementEarned> getAchievementEarnedById(Long id) {
        return achievementEarnedRepository.findById(id);
    }

    public List<AchievementEarned> getAchievementsEarnedByUserId(Long userId) {
        return achievementEarnedRepository.findByUserId(userId);
    }

    public AchievementEarned saveAchievementEarned(AchievementEarned achievementEarned) {
        return achievementEarnedRepository.save(achievementEarned);
    }

    public void deleteAchievementEarned(Long id) {
        achievementEarnedRepository.deleteById(id);
    }
}
