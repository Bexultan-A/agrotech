package com.example.agrotech.controller;

import com.example.agrotech.model.Logs;
import com.example.agrotech.service.GamificationService;
import com.example.agrotech.service.LogsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogsController {

    private final LogsService logsService;
    private final GamificationService gamificationService;

    @GetMapping
    public List<Logs> getAllLogs() {
        return logsService.getAllLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logs> getLogById(@PathVariable Long id) {
        Optional<Logs> log = logsService.getLogById(id);
        return log.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Logs> getLogsByUserId(@PathVariable Long userId) {
        return logsService.getLogsByUserId(userId);
    }

    @PostMapping
    public Logs createLog(@RequestBody Logs log) {
        gamificationService.updateRank(log.getGarden().getOwner().getId());
        return logsService.saveLog(log);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable Long id) {
        logsService.deleteLog(id);
        return ResponseEntity.noContent().build();
    }
}
