package com.nhson.userservice.application.dto;

import java.time.LocalDate;
import java.util.Set;

public class CustomEventDto {
    private Long id;
    private String name;
    private int lunarDay;
    private int lunarMonth;
    private LocalDate solarDate;
    private String description;
    private Set<StepDto> ritualSteps;
    private Set<OfferingDto> offerings;

    public CustomEventDto(Long id, String name, int lunarDay, int lunarMonth, LocalDate solarDate, String description, Set<StepDto> ritualSteps, Set<OfferingDto> offerings) {
        this.id = id;
        this.name = name;
        this.lunarDay = lunarDay;
        this.lunarMonth = lunarMonth;
        this.solarDate = solarDate;
        this.description = description;
        this.ritualSteps = ritualSteps;
        this.offerings = offerings;
    }

    public Long getId() {return id;}
    public String getName() {return name;}
    public int getLunarDay() {return lunarDay;}
    public int getLunarMonth() {return lunarMonth;}
    public LocalDate getSolarDate() {return solarDate;}
    public String getDescription() {return description;}
    public Set<StepDto> getRitualSteps() {return ritualSteps;}
    public Set<OfferingDto> getOfferings() {return offerings;}
}
