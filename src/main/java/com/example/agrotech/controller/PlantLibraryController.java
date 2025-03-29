package com.example.agrotech.controller;

import com.example.agrotech.model.PlantLibrary;
import com.example.agrotech.service.PlantLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class PlantLibraryController {

    private final PlantLibraryService plantLibraryService;

    @GetMapping
    public List<PlantLibrary> getAllPlants() {
        return plantLibraryService.getAllPlants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantLibrary> getPlantById(@PathVariable Long id) {
        Optional<PlantLibrary> plant = plantLibraryService.getPlantById(id);
        return plant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PlantLibrary> getPlantByName(@PathVariable String name) {
        Optional<PlantLibrary> plant = plantLibraryService.getPlantByName(name);
        return plant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PlantLibrary createPlant(@RequestBody PlantLibrary plant) {
        return plantLibraryService.savePlant(plant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantLibraryService.deletePlant(id);
        return ResponseEntity.noContent().build();
    }
}
