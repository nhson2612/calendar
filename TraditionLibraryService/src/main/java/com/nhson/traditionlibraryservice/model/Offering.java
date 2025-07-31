package com.nhson.traditionlibraryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "offering")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;
    private String note;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private TraditionalEvent event;
}


