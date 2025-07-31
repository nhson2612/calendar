package com.nhson.registrationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", path = "${application.feign-clients.profile-service.path}")
public interface ProfileClient {
    @PostMapping()
    ResponseEntity<ProfileResponse> createProfile(@RequestBody RegisterReq req);
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProfile(@PathVariable Long id);
}
