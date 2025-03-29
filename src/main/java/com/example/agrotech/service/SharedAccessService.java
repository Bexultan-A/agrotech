package com.example.agrotech.service;

import com.example.agrotech.model.SharedAccess;
import com.example.agrotech.repository.SharedAccessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SharedAccessService {

    private final SharedAccessRepository sharedAccessRepository;

    public List<SharedAccess> getAllSharedAccess() {
        return sharedAccessRepository.findAll();
    }

    public Optional<SharedAccess> getSharedAccessById(Long id) {
        return sharedAccessRepository.findById(id);
    }

    public List<SharedAccess> getSharedAccessByGarden(Long gardenId) {
        return sharedAccessRepository.findByGardenId(gardenId);
    }

    public List<SharedAccess> getSharedAccessByUser(Long userId) {
        return sharedAccessRepository.findByUserId(userId);
    }

    public SharedAccess saveSharedAccess(SharedAccess sharedAccess) {
        return sharedAccessRepository.save(sharedAccess);
    }

    public void deleteSharedAccess(Long id) {
        sharedAccessRepository.deleteById(id);
    }
}
