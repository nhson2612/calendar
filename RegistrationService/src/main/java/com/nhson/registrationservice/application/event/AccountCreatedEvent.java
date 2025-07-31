package com.nhson.registrationservice.application.event;

import com.nhson.registrationservice.client.RegisterReq;
import org.springframework.context.ApplicationEvent;

public class AccountCreatedEvent extends ApplicationEvent {
    private final RegisterReq req;
    public AccountCreatedEvent(Object source, RegisterReq req) {
        super(source);
        this.req = req;
    }
    public RegisterReq getReq() {return req;}
}
