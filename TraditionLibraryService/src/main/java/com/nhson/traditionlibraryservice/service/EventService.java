package com.nhson.traditionlibraryservice.service;

import com.nhson.traditionlibraryservice.dto.PagedResponse;
import com.nhson.traditionlibraryservice.dto.TraditionalEventDto;
import com.nhson.traditionlibraryservice.helper.EventPaginationHelper;
import com.nhson.traditionlibraryservice.mapper.TraditionalEventMapper;
import com.nhson.traditionlibraryservice.model.EventType;
import com.nhson.traditionlibraryservice.model.Region;
import com.nhson.traditionlibraryservice.model.TraditionalEvent;
import com.nhson.traditionlibraryservice.repo.EventRepository;
import com.nhson.traditionlibraryservice.spec.TraditionalEventSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TraditionalEventMapper mapper;

    public EventService(EventRepository eventRepository, TraditionalEventMapper mapper) {
        this.eventRepository = eventRepository;
        this.mapper = mapper;
    }

    public PagedResponse<TraditionalEventDto> searchEvents(String keyword, Integer lunarDay,
                                                           Integer lunarMonth, EventType type,
                                                           Region region, Boolean repeatAnnually,
                                                           Integer page, Integer size,
                                                           String sortBy, String sortOrder) {

        Specification<TraditionalEvent> spec = null;
        spec = TraditionalEventSpecifications.and(spec, TraditionalEventSpecifications.hasKeyword(keyword));
        for (Specification<TraditionalEvent> traditionalEventSpecification : Arrays.asList(TraditionalEventSpecifications.hasLunarDay(lunarDay), TraditionalEventSpecifications.hasLunarMonth(lunarMonth), TraditionalEventSpecifications.hasType(type), TraditionalEventSpecifications.hasRegion(region), TraditionalEventSpecifications.hasRepeatAnnually(repeatAnnually))) {
            spec = TraditionalEventSpecifications.and(spec, traditionalEventSpecification);
        }

        Pageable pageable = EventPaginationHelper.createPageable(page,size,sortBy,sortOrder);
        Page<TraditionalEvent> eventPage = eventRepository.findAll(spec, pageable);
        return getTraditionalEventDtoPagedResponse(eventPage);
    }


    public PagedResponse<TraditionalEventDto> getAllEvents(Integer page, Integer size,
                                                           String sortBy, String sortOrder) {
        Pageable pageable = EventPaginationHelper.createPageable(page,size,sortBy,sortOrder);
        Page<TraditionalEvent> eventPage = eventRepository.findAll(pageable);
        return getTraditionalEventDtoPagedResponse(eventPage);
    }

    public TraditionalEventDto getEventById(Long id) {
        TraditionalEvent event = eventRepository.findEventById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
        return mapper.toDto(event);
    }

    public TraditionalEventDto createEvent(TraditionalEventDto eventDto) {
        TraditionalEvent event = mapper.toEntity(eventDto);
        TraditionalEvent savedEvent = eventRepository.save(event);
        return mapper.toDto(savedEvent);
    }

    public TraditionalEventDto updateEvent(Long id, TraditionalEventDto eventDto) {
        TraditionalEvent existingEvent = eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));

        // Update fields
        existingEvent.setName(eventDto.getName());
        existingEvent.setAlias(eventDto.getAlias());
        existingEvent.setLunarDay(eventDto.getLunarDay());
        existingEvent.setLunarMonth(eventDto.getLunarMonth());
        existingEvent.setSolarDate(eventDto.getSolarDate());
        existingEvent.setDescription(eventDto.getDescription());
        existingEvent.setType(eventDto.getType());
        existingEvent.setRepeatAnnually(eventDto.isRepeatAnnually());
        existingEvent.setImportanceLevel(eventDto.getImportanceLevel());
        existingEvent.setTags(eventDto.getTags());
        existingEvent.setRegions(eventDto.getRegions());

        TraditionalEvent updatedEvent = eventRepository.save(existingEvent);
        return mapper.toDto(updatedEvent);
    }

    public void deleteEvent(Long id) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Event not found with id: " + id);
        }
        eventRepository.deleteById(id);
    }

    private PagedResponse<TraditionalEventDto> getTraditionalEventDtoPagedResponse(Page<TraditionalEvent> eventPage) {
        List<TraditionalEventDto> events = eventPage.getContent().stream()
                .map(mapper::toDto)
                .toList();
        return PagedResponse.<TraditionalEventDto>builder()
                .content(events)
                .currentPage(eventPage.getNumber())
                .totalPages(eventPage.getTotalPages())
                .totalElements(eventPage.getTotalElements())
                .pageSize(eventPage.getSize())
                .hasNext(eventPage.hasNext())
                .hasPrevious(eventPage.hasPrevious())
                .isFirst(eventPage.isFirst())
                .isLast(eventPage.isLast())
                .build();
    }
}