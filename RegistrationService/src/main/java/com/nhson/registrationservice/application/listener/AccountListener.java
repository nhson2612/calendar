package com.nhson.registrationservice.application.listener;

import com.nhson.registrationservice.application.event.AccountCreatedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AccountListener {

    private final RabbitTemplate rabbitTemplate;

    public AccountListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @EventListener
    public void onAccountCreated(AccountCreatedEvent event){
        String email = event.getReq().email();
        String telegramId = event.getReq().telegramId();
        // TODO : gửi đến queue để gửi mail hoặc tele
    }
}