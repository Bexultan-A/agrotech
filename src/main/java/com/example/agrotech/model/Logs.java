package com.example.agrotech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "garden_id", nullable = false)
    private Garden garden;

    private long quantityHarvested;

    @Column(nullable = false)
    private int healthPoints; // Starts at 100 HP

    @Column(nullable = false)
    private int careLevel; // Tracks how well the garden is maintained
}
