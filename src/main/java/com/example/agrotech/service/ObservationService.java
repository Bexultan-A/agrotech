package com.example.agrotech.service;

import com.example.agrotech.model.Observation;
import com.example.agrotech.repository.ObservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObservationService {

    private final ObservationRepository observationRepository;

    public List<Observation> getAllObservations() {
        return observationRepository.findAll();
    }

    public Optional<Observation> getObservationById(Long id) {
        return observationRepository.findById(id);
    }

    public List<Observation> getObservationsByGarden(Long gardenId) {
        return observationRepository.findByGardenId(gardenId);
    }

    public Observation saveObservation(Observation observation) {
        return observationRepository.save(observation);
    }

    public void deleteObservation(Long id) {
        observationRepository.deleteById(id);
    }
}
