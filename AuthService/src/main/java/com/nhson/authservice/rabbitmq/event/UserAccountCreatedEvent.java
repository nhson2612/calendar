package com.nhson.authservice.rabbitmq.event;

public class UserAccountCreatedEvent {
    private Long userId;

    public UserAccountCreatedEvent(Long userId) {
        this.userId = userId;
    }
}
