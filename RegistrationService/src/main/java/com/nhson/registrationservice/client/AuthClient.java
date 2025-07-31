package com.nhson.registrationservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-server", path = "${application.feign-clients.auth-server.path}")
public interface AuthClient {
    @PostMapping()
    ResponseEntity<?> createAccount(@RequestBody RegisterReq req);
}