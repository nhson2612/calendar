package com.nhson.userservice.application.model;

import com.nhson.userservice.application.dto.StepDto;
import jakarta.persistence.*;

@Entity
@Table(name = "ritual_step")
public class RitualStep {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int stepOrder;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private CustomEvent event;

    public RitualStep(Long id, int stepOrder, String title, String description, CustomEvent event) {
        this.id = id;
        this.stepOrder = stepOrder;
        this.title = title;
        this.description = description;
        this.event = event;
    }
    public RitualStep(StepDto stepDto) {
        this.id = stepDto.getId();
        this.stepOrder = stepDto.getStepOrder();
        this.title = stepDto.getTitle();
        this.description = stepDto.getDescription();
        this.event = new CustomEvent();
        this.event.setId(stepDto.getEventId());
    }
    public RitualStep() {
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public int getStepOrder() {return stepOrder;}
    public void setStepOrder(int stepOrder) {this.stepOrder = stepOrder;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public CustomEvent getEvent() {return event;}
    public void setEvent(CustomEvent event) {this.event = event;}
}

