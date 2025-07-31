package com.nhson.authservice.rabbitmq.event;

public class UserAccountCreationFailedEvent {
    private Long profileId;
    private String reason;

    public UserAccountCreationFailedEvent(Long profileId, String reason) {
        this.profileId = profileId;
        this.reason = reason;
    }
}
