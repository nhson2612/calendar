package com.nhson.userservice.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${custom.rabbitmq.exchange}")
    private String eventExchange;
//    @Value("${custom.rabbitmq.queues.userCreated}")
//    private String authUserCreatedQueue;
    @Value("${custom.rabbitmq.queues.userFailed}")
    private String authUserFailedQueue;

    @Bean
    public TopicExchange centralExchange() {
        return new TopicExchange("central.event.exchange");
    }
    @Bean
    public Queue authUserFailedQueue() {
        return new Queue(authUserFailedQueue);
    }
    @Bean
    public Binding bindingAuthFailed() {
        return BindingBuilder
                .bind(authUserFailedQueue())
                .to(centralExchange())
                .with("auth.user.failed");
    }
//    @Bean
//    public Queue authUserCreatedQueue() {

//        return new Queue(authUserCreatedQueue);

//    }
//    @Bean
//    public Binding bindingAuthCreated() {
//        return BindingBuilder
//                .bind(authUserCreatedQueue())
//                .to(centralExchange())
//                .with("auth.user.created");

//    }

}