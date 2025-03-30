package com.example.agrotech.dto;

import com.example.agrotech.model.User;
import lombok.Data;

import java.util.List;

@Data
public class StatisticsDTO {
    private int careLevel = 0;
    private int successfullyHarvested = 0;
    private int averageGrowthTime = 0;
    private int averageHarvestQuantity = 0;
    private List<User> leaderboard = null;
}
