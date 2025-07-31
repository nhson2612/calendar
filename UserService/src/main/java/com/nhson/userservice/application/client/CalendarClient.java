package com.nhson.userservice.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "calendar-service", path = "${application.feign-clients.calendar-service.path}")
public interface CalendarClient {
    @GetMapping("/api/v1/calendar/s2l")
    ResponseEntity<LunarDate> solar2Lunar(@RequestParam("dd") int day, @RequestParam("mm") int month, @RequestParam("yy") int year);
    @GetMapping("/api/v1/calendar/l2s")
    ResponseEntity<SolarDate> lunar2Solar(@RequestParam("dd") int day, @RequestParam("mm") int month, @RequestParam("yy") int year, @RequestParam(value = "leap", required = false, defaultValue = "false") boolean leap);
}
