package com.example.agrotech.repository;

import com.example.agrotech.model.SharedAccess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharedAccessRepository extends JpaRepository<SharedAccess, Long> {
    List<SharedAccess> findByGardenId(Long gardenId);
    List<SharedAccess> findByUserId(Long userId);
}
