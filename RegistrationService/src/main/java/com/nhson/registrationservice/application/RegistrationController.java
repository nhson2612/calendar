package com.nhson.registrationservice.application;

import com.nhson.registrationservice.application.event.AccountCreatedEvent;
import com.nhson.registrationservice.client.AuthClient;
import com.nhson.registrationservice.client.ProfileClient;
import com.nhson.registrationservice.client.RegisterReq;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    @Value("${custom.rabbitmq.exchange}")
    private String exchangeName;
    private final ProfileClient profileClient;
    private final AuthClient authClient;
    private final RabbitTemplate rabbitTemplate;
    private final ApplicationEventPublisher publisher;

    public RegistrationController(ProfileClient profileClient, AuthClient authClient, RabbitTemplate rabbitTemplate, ApplicationEventPublisher publisher) {
        this.profileClient = profileClient;
        this.authClient = authClient;
        this.rabbitTemplate = rabbitTemplate;
        this.publisher = publisher;
    }

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody RegisterReq req){
        var profileRes = profileClient.createProfile(req);
        if (profileRes.getStatusCode() != HttpStatus.CREATED) {
            return ResponseEntity.badRequest().body("Tạo hồ sơ thất bại");
        }
        var authRes = authClient.createAccount(req);
        if (authRes.getStatusCode() != HttpStatus.CREATED) {
            UserAccountCreationFailedEvent failedEvent = new UserAccountCreationFailedEvent(profileRes.getBody().profileId(),"Tạo hồ sơ thất bại");
            rabbitTemplate.convertAndSend(exchangeName, "auth.user.failed", failedEvent);
            return ResponseEntity.badRequest().body("Tạo tài khoản thất bại");
        }
        publisher.publishEvent(new AccountCreatedEvent(this,req));
        return ResponseEntity.ok("Đăng ký thành công!");
    }
}