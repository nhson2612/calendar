package com.nhson.traditionlibraryservice.controller;

import com.nhson.traditionlibraryservice.dto.PagedResponse;
import com.nhson.traditionlibraryservice.dto.TraditionalEventDto;
import com.nhson.traditionlibraryservice.model.EventType;
import com.nhson.traditionlibraryservice.model.Region;
import com.nhson.traditionlibraryservice.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService  eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/search")
    public ResponseEntity<PagedResponse<TraditionalEventDto>> searchEvents(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer lunarDay,
            @RequestParam(required = false) Integer lunarMonth,
            @RequestParam(required = false) EventType type,
            @RequestParam(required = false) Region region,
            @RequestParam(required = false) Boolean repeatAnnually,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        PagedResponse<TraditionalEventDto> response = eventService.searchEvents(
                keyword, lunarDay, lunarMonth, type, region, repeatAnnually,
                page, size, sortBy, sortOrder);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<TraditionalEventDto>> getAllEvents(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {
        PagedResponse<TraditionalEventDto> response = eventService.getAllEvents(
                page, size, sortBy, sortOrder);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TraditionalEventDto> updateEvent(
            @PathVariable Long id,
            @RequestBody TraditionalEventDto eventDto) {
        TraditionalEventDto updatedEvent = eventService.updateEvent(id, eventDto);
        return ResponseEntity.ok(updatedEvent);
    }

    @PostMapping
    public ResponseEntity<TraditionalEventDto> createEvent(
            @RequestBody TraditionalEventDto eventDto) {
        TraditionalEventDto createdEvent = eventService.createEvent(eventDto);
        return ResponseEntity.ok(createdEvent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TraditionalEventDto> findEventById(@PathVariable Integer id) {
        return ResponseEntity.ok(eventService.getEventById(id.longValue()));
    }
}
