package com.nhson.traditionlibraryservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "traditional_event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TraditionalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String alias;

    @Column(name = "lunar_day")
    private int lunarDay;

    @Column(name = "lunar_month")
    private int lunarMonth;

    private LocalDate solarDate;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private boolean repeatAnnually;

    private int importanceLevel;

    @ElementCollection
    @CollectionTable(name = "traditional_event_tag", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "tag")
    private Set<String> tags;

    @ElementCollection
    @CollectionTable(name = "traditional_event_region", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "region")
    @Enumerated(EnumType.STRING)
    private Set<Region> regions;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RitualStep> ritualSteps;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Offering> offerings;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Media> media;

}