package com.example.agrotech.controller;

import com.example.agrotech.dto.StatisticsDTO;
import com.example.agrotech.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/{userId}")
    public ResponseEntity<StatisticsDTO> getStatistics(@PathVariable long userId) {
        StatisticsDTO statistics = statisticsService.getStatistics(userId);
        return ResponseEntity.ok(statistics);
    }
}
