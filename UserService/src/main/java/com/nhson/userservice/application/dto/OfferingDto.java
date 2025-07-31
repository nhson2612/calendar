package com.nhson.userservice.application.dto;

public class OfferingDto {
    private Long offeringId;
    private String name;
    private String category;
    private String note;
    private Long eventId;

    public OfferingDto(Long offeringId, String name, String category, String note, Long eventId) {
        this.offeringId = offeringId;
        this.name = name;
        this.category = category;
        this.note = note;
        this.eventId = eventId;
    }

    public Long getOfferingId() {return offeringId;}
    public String getName() {return name;}
    public String getCategory() {return category;}
    public String getNote() {return note;}
    public Long getEventId() {return eventId;}
}