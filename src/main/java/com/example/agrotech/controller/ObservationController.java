package com.example.agrotech.controller;

import com.example.agrotech.model.Observation;
import com.example.agrotech.service.ObservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/observations")
@RequiredArgsConstructor
public class ObservationController {

    private final ObservationService observationService;

    @GetMapping
    public List<Observation> getAllObservations() {
        return observationService.getAllObservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Observation> getObservationById(@PathVariable Long id) {
        Optional<Observation> observation = observationService.getObservationById(id);
        return observation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/garden/{gardenId}")
    public List<Observation> getObservationsByGarden(@PathVariable Long gardenId) {
        return observationService.getObservationsByGarden(gardenId);
    }

    @PostMapping
    public Observation createObservation(@RequestBody Observation observation) {
        return observationService.saveObservation(observation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObservation(@PathVariable Long id) {
        observationService.deleteObservation(id);
        return ResponseEntity.noContent().build();
    }
}
