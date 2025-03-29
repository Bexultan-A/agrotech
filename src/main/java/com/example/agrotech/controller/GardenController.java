package com.example.agrotech.controller;

import com.example.agrotech.model.Garden;
import com.example.agrotech.service.GardenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gardens")
@RequiredArgsConstructor
public class GardenController {

    private final GardenService gardenService;

    @GetMapping
    public List<Garden> getAllGardens() {
        return gardenService.getAllGardens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garden> getGardenById(@PathVariable Long id) {
        Optional<Garden> garden = gardenService.getGardenById(id);
        return garden.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/owner/{ownerId}")
    public List<Garden> getGardensByOwner(@PathVariable Long ownerId) {
        return gardenService.getGardensByOwner(ownerId);
    }

    @PostMapping
    public Garden createGarden(@RequestBody Garden garden) {
        return gardenService.saveGarden(garden);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGarden(@PathVariable Long id) {
        gardenService.deleteGarden(id);
        return ResponseEntity.noContent().build();
    }
}
