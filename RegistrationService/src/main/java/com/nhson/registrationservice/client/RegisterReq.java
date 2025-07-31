package com.nhson.registrationservice.client;

public record RegisterReq(String fullName,String username,String password,String email,String telegramId) {}