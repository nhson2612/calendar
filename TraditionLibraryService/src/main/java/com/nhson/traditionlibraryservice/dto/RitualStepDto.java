package com.nhson.traditionlibraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RitualStepDto {
    private Long id;
    private int stepOrder;
    private String title;
    private String description;
    private Long eventId;
}
