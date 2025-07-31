package com.nhson.traditionlibraryservice.dto;

import com.nhson.traditionlibraryservice.model.EventType;
import com.nhson.traditionlibraryservice.model.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TraditionalEventDto {
    private Long id;
    private String name;
    private String alias;
    private int lunarDay;
    private int lunarMonth;
    private LocalDate solarDate;
    private String description;
    private EventType type;
    private boolean repeatAnnually;
    private int importanceLevel;
    private Set<String> tags;
    private Set<Region> regions;
    private Set<RitualStepDto> ritualSteps;
    private Set<OfferingDto> offerings;
    private Set<MediaDto> media;
}