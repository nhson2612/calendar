package com.nhson.userservice.application.model;

import com.nhson.userservice.application.dto.OfferingDto;
import jakarta.persistence.*;

@Entity
@Table(name = "offering")
public class Offering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private String note;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private CustomEvent event;

    public Offering(Long id, String name, String category, String note, CustomEvent event) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.note = note;
        this.event = event;
    }
    public Offering(OfferingDto offeringDto) {
        this.id = offeringDto.getOfferingId();
        this.name = offeringDto.getName();
        this.category = offeringDto.getCategory();
        this.note = offeringDto.getNote();
        this.event = new CustomEvent();
        this.event.setId(offeringDto.getEventId());
    }
    public Offering() {}

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    public String getNote() {return note;}
    public void setNote(String note) {this.note = note;}
    public CustomEvent getEvent() {return event;}
    public void setEvent(CustomEvent event) {this.event = event;}
}
