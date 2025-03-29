package com.example.agrotech.service;

import com.example.agrotech.model.Achievement;
import com.example.agrotech.repository.AchievementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AchievementService {

    private final AchievementRepository achievementRepository;

    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    public Optional<Achievement> getAchievementById(Long id) {
        return achievementRepository.findById(id);
    }

    public Achievement saveAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }
}
