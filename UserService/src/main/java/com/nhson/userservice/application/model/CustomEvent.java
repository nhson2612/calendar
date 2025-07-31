package com.nhson.userservice.application.model;

import com.nhson.userservice.application.dto.CustomEventDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "user-event")
public class CustomEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int lunarDay;
    private int lunarMonth;
    private LocalDate solarDate;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<RitualStep> ritualSteps;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Offering> offerings;
    @ManyToOne
    @JoinColumn(name = "profile_id")
    private UserProfile profile;

    public CustomEvent() {
    }

    public CustomEvent(CustomEventDto customEventDto) {
        this.id = customEventDto.getId();
        this.name = customEventDto.getName();
        this.lunarDay = customEventDto.getLunarDay();
        this.lunarMonth = customEventDto.getLunarMonth();
        this.solarDate = customEventDto.getSolarDate();
        this.description = customEventDto.getDescription();
        this.ritualSteps = customEventDto.getRitualSteps().stream()
                .map(RitualStep::new)
                .collect(Collectors.toSet());
        this.offerings = customEventDto.getOfferings().stream()
                .map(Offering::new)
                .collect(Collectors.toSet());
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public int getLunarDay() {return lunarDay;}
    public int getLunarMonth() {return lunarMonth;}
    public LocalDate getSolarDate() {return solarDate;}
    public String getDescription() {return description;}
    public Set<RitualStep> getRitualSteps() {return ritualSteps;}
    public Set<Offering> getOfferings() {return offerings;}
    public UserProfile getProfile() {return profile;}

    public void setId(Long id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setLunarDay(int lunarDay) {this.lunarDay = lunarDay;}
    public void setLunarMonth(int lunarMonth) {this.lunarMonth = lunarMonth;}
    public void setSolarDate(LocalDate solarDate) {this.solarDate = solarDate;}
    public void setDescription(String description) {this.description = description;}
    public void setRitualSteps(Set<RitualStep> ritualSteps) {this.ritualSteps = ritualSteps;}
    public void setOfferings(Set<Offering> offerings) {this.offerings = offerings;}
    public void setProfile(UserProfile profile) {this.profile = profile;}
}
