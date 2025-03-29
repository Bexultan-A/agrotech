package com.example.agrotech.service;

import com.example.agrotech.model.PlantLibrary;
import com.example.agrotech.repository.PlantLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlantLibraryService {

    private final PlantLibraryRepository plantLibraryRepository;

    public List<PlantLibrary> getAllPlants() {
        return plantLibraryRepository.findAll();
    }

    public Optional<PlantLibrary> getPlantById(Long id) {
        return plantLibraryRepository.findById(id);
    }

    public Optional<PlantLibrary> getPlantByName(String name) {
        return plantLibraryRepository.findByName(name);
    }

    public PlantLibrary savePlant(PlantLibrary plant) {
        return plantLibraryRepository.save(plant);
    }

    public void deletePlant(Long id) {
        plantLibraryRepository.deleteById(id);
    }
}
