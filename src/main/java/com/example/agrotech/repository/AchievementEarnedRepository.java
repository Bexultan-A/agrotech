package com.example.agrotech.repository;

import com.example.agrotech.model.Achievement;
import com.example.agrotech.model.AchievementEarned;
import com.example.agrotech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AchievementEarnedRepository extends JpaRepository<AchievementEarned, Long> {
    List<AchievementEarned> findByUserId(Long userId);
    Optional<AchievementEarned> findByUserAndAchievement(User user, Achievement achievement);
}
