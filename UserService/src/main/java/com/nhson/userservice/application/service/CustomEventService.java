package com.nhson.userservice.application.service;

import com.nhson.userservice.application.dto.CustomEventDto;
import com.nhson.userservice.application.model.CustomEvent;
import com.nhson.userservice.application.repository.CustomEventRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomEventService {

    private final CustomEventRepository eventRepository;

    public CustomEventService(CustomEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void addCustomEvent(CustomEventDto customEventDto) {
        CustomEvent customEvent = new CustomEvent(customEventDto);
        eventRepository.save(customEvent);
    }
}