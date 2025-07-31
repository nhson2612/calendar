package com.nhson.userservice.application.dto;

public class StepDto {
    private Long id;
    private int stepOrder;
    private String title;
    private String description;
    private Long eventId;

    public StepDto(Long id, int stepOrder, String title, String description, Long eventId) {
        this.id = id;
        this.stepOrder = stepOrder;
        this.title = title;
        this.description = description;
        this.eventId = eventId;
    }

    public Long getId() {return id;}
    public int getStepOrder() {return stepOrder;}
    public String getTitle() {return title;}
    public String getDescription() {return description;}
    public Long getEventId() {return eventId;}
}