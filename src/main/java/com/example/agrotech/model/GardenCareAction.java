package com.example.agrotech.model;

import lombok.Getter;

@Getter
public enum GardenCareAction {
    WATERING(10, 2),
    IGNORING(-20, -5), // Neglect reduces HP and care level
    HARVESTING(5, 1);

    private final int hpChange;
    private final int careChange;

    GardenCareAction(int hpChange, int careChange) {
        this.hpChange = hpChange;
        this.careChange = careChange;
    }

}
