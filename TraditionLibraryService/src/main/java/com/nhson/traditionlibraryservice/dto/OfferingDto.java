package com.nhson.traditionlibraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferingDto {
    private Long id;
    private String name;
    private String category;
    private String note;
    private Long eventId;
}