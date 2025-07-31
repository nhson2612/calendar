package com.nhson.authservice.application.controller;

import com.nhson.authservice.application.dto.RegisterReq;
import com.nhson.authservice.application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody RegisterReq req) {
        userService.createAccount(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
