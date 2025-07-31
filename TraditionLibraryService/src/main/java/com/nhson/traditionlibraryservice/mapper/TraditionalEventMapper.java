package com.nhson.traditionlibraryservice.mapper;

import com.nhson.traditionlibraryservice.dto.TraditionalEventDto;
import com.nhson.traditionlibraryservice.model.TraditionalEvent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TraditionalEventMapper {

    TraditionalEventDto toDto(TraditionalEvent entity);

    TraditionalEvent toEntity(TraditionalEventDto dto);

    void updateEntityFromDto(TraditionalEventDto dto, @MappingTarget TraditionalEvent entity);
}