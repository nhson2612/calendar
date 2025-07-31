//package com.nhson.authservice.rabbitmq.listener;
//
//import com.nhson.authservice.application.model.Role;
//import com.nhson.authservice.application.model.User;
//import com.nhson.authservice.application.repository.UserRepository;
//import com.nhson.authservice.rabbitmq.command.CreateUserAccountCommand;
//import com.nhson.authservice.rabbitmq.event.UserAccountCreatedEvent;
//import com.nhson.authservice.rabbitmq.event.UserAccountCreationFailedEvent;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AccountListener {
//
//    private final RabbitTemplate rabbitTemplate;
//    private final PasswordEncoder passwordEncoder;
//    private final UserRepository userRepository;
//
//    public AccountListener(RabbitTemplate rabbitTemplate, PasswordEncoder passwordEncoder, UserRepository userRepository) {
//        this.rabbitTemplate = rabbitTemplate;
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//    }
//
//    @RabbitListener(queues = "auth-service.create-user.queue")
//    public void handleCreateUser(CreateUserAccountCommand command) {
//        try {
//            if (userRepository.existsByUsername(command.getUsername())) {
//                throw new RuntimeException("Username already exists");
//            }
//            User user = new User(null,command.getUsername(), passwordEncoder.encode(command.getPassword()), Role.USER);
//            User saved = userRepository.save(user);
//            UserAccountCreatedEvent successEvent = new UserAccountCreatedEvent(saved.getId());
//            rabbitTemplate.convertAndSend("central.event.exchange", "auth.user.created", successEvent);
//        } catch (Exception e) {
//            UserAccountCreationFailedEvent failedEvent = new UserAccountCreationFailedEvent(command.getProfileId(), e.getMessage());
//            rabbitTemplate.convertAndSend("central.event.exchange", "auth.user.failed", failedEvent);
//        }
//    }
//}