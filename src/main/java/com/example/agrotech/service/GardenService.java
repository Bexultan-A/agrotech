package com.example.agrotech.service;

import com.example.agrotech.model.Garden;
import com.example.agrotech.repository.GardenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GardenService {

    private final GardenRepository gardenRepository;

    public List<Garden> getAllGardens() {
        return gardenRepository.findAll();
    }

    public Optional<Garden> getGardenById(Long id) {
        return gardenRepository.findById(id);
    }

    public List<Garden> getGardensByOwner(Long ownerId) {
        return gardenRepository.findByOwnerId(ownerId);
    }

    public Garden saveGarden(Garden garden) {
        garden.setLabel("#" + (garden.getPlantType().length() >= 3 ? garden.getPlantType().substring(0, 3) : garden.getPlantType()) + garden.getId());
        return gardenRepository.save(garden);
    }

    public void deleteGarden(Long id) {
        gardenRepository.deleteById(id);
    }
}
