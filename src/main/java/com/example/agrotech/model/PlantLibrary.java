package com.example.agrotech.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "plant_library")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantLibrary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private int growingDays;

    private int wateringInterval;

    private int waterQuantity;

    private String waterCondition;

    private BigDecimal light_level;

    private BigDecimal air_humidity;

    private BigDecimal air_temperature;

    @Column(length = 1000)
    private String notes;

}
