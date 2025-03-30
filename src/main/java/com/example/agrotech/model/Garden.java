package com.example.agrotech.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "gardens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String label;

    @ManyToOne
    @JoinColumn(name = "plant_library_id", nullable = false)
    private PlantLibrary plantType;

    private String substrate;

    @Column(nullable = false)
    private LocalDate sowingDate;

    private LocalDate harvestDate;

    private long quantitySowed;

    @Column(nullable = false)
    private String status; // Active, Harvested, Issue

    @Column(nullable = false)
    private int healthPoints = 100; // Starts at 100 HP

    @Column(nullable = false)
    private int careLevel = 0; // Tracks how well the garden is maintained
}