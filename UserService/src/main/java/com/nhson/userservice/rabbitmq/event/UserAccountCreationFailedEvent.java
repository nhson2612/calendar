package com.nhson.userservice.rabbitmq.event;

public class UserAccountCreationFailedEvent {
    private Long profileId;
    private String reason;

    public UserAccountCreationFailedEvent(Long profileId, String reason) {
        this.profileId = profileId;
        this.reason = reason;
    }

    public Long getProfileId() {
        return profileId;
    }

    public String getReason() {
        return reason;
    }
}
