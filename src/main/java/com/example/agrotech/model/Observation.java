package com.example.agrotech.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Base64;

@Entity
@Table(name = "observations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "garden_id", nullable = false)
    private Garden garden;

    @Column(nullable = false)
    private LocalDate date;

    private Float heightCm;

    private byte[] photoUrl;

    @Column(length = 1000)
    private String notes;

    private BigDecimal light_level;

    private BigDecimal air_humidity;

    private BigDecimal air_temperature;

    private boolean watering;
}