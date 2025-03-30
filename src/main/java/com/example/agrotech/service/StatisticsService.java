package com.example.agrotech.service;

import com.example.agrotech.dto.StatisticsDTO;
import com.example.agrotech.model.Garden;
import com.example.agrotech.model.Logs;
import com.example.agrotech.model.User;
import com.example.agrotech.repository.GardenRepository;
import com.example.agrotech.repository.LogsRepository;
import com.example.agrotech.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final LogsRepository logsRepository;
    private final GardenRepository gardenRepository;
    private final UserRepository userRepository;


    public StatisticsDTO getStatistics(long userId) {
        List<Logs> logs = logsRepository.findByUserId(userId);

        int careLevel = (int) logs.stream()
                .mapToInt(Logs::getCareLevel)
                .average()
                .orElse(0.0);



        long successfullyHarvestedGardens = gardenRepository.countByOwnerIdAndStatus(userId, "Harvested");
        long allGardens = gardenRepository.countByOwnerId(userId);
        int successfullyHarvested = (int) (successfullyHarvestedGardens/allGardens);

        List<Garden> harvestedGardens = gardenRepository.findHarvestedGardensWithDates(userId);

        int averageGrowthTime = 0;

        long totalDays = harvestedGardens.stream()
                .mapToLong(g -> ChronoUnit.DAYS.between(g.getSowingDate(), g.getHarvestDate()))
                .sum();

        averageGrowthTime = (int) (totalDays / harvestedGardens.size());


        int averageHarvestQuantity = 0;

        long totalQuantity = logs.stream()
                .mapToLong(Logs::getQuantityHarvested)
                .sum();

        averageHarvestQuantity = (int) (totalQuantity/ logs.size());

        List<User> leaderboard = userRepository.findTopUsers(PageRequest.of(0, 10));

        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setCareLevel(careLevel);
        statisticsDTO.setSuccessfullyHarvested(successfullyHarvested);
        statisticsDTO.setAverageGrowthTime(averageGrowthTime);
        statisticsDTO.setAverageHarvestQuantity(averageHarvestQuantity);
        statisticsDTO.setLeaderboard(leaderboard);

        return statisticsDTO;
    }
}
