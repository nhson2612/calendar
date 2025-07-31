package com.nhson.traditionlibraryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ritual_step")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RitualStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int stepOrder;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private TraditionalEvent event;
}

