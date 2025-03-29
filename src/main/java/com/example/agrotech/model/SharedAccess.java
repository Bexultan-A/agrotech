package com.example.agrotech.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shared_access")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SharedAccess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "garden_id", nullable = false)
    private Garden garden;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String role; // View, Edit
}
