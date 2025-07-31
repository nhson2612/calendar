package com.nhson.userservice.application.controller;

import com.nhson.userservice.application.dto.ProfileResponse;
import com.nhson.userservice.application.dto.RegisterReq;
import com.nhson.userservice.application.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody RegisterReq req){
        ProfileResponse response = userProfileService.createUserProfile(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}