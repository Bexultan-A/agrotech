package com.example.agrotech.repository;

import com.example.agrotech.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u JOIN u.userStats us ORDER BY us.experiencePoints DESC")
    List<User> findTopUsers(Pageable pageable);
}
