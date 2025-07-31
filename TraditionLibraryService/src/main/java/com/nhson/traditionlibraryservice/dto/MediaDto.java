package com.nhson.traditionlibraryservice.dto;

import com.nhson.traditionlibraryservice.model.MediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaDto {
    private Long id;
    private String url;
    private MediaType type;
    private String description;
    private Long eventId;
}
