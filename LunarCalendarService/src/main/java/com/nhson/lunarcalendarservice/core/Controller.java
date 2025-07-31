package com.nhson.lunarcalendarservice.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calendar")
public class Controller {

    @GetMapping("/s2l")
    public ResponseEntity<LunarDate> solar2Lunar(@RequestParam("dd") int day, @RequestParam("mm") int month, @RequestParam("yyyy") int year) {
        return ResponseEntity.ok(Converter.solarToLunar(day, month, year));
    }
    @GetMapping("/l2s")
    public ResponseEntity<SolarDate> lunar2Solar(@RequestParam("dd") int day, @RequestParam("mm") int month, @RequestParam("yyyy") int year, @RequestParam(value = "leap", required = false, defaultValue = "false") boolean leap) {
        return ResponseEntity.ok(Converter.lunarToSolar(day, month, year, leap));
    }
}