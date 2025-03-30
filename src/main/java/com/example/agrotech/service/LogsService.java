package com.example.agrotech.service;

import com.example.agrotech.model.Logs;
import com.example.agrotech.repository.LogsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogsService {

    private final LogsRepository logsRepository;

    public List<Logs> getAllLogs() {
        return logsRepository.findAll();
    }

    public Optional<Logs> getLogById(Long id) {
        return logsRepository.findById(id);
    }

    public List<Logs> getLogsByUserId(Long userId) {
        return logsRepository.findByUserId(userId);
    }

    public Logs saveLog(Logs log) {
        return logsRepository.save(log);
    }

    public void deleteLog(Long id) {
        logsRepository.deleteById(id);
    }
}
