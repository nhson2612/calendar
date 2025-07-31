//package com.nhson.authservice.rabbitmq.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RabbitMQConfig {
//
//    @Value("${custom.rabbitmq.exchange}")
//    private String eventExchange;
//    @Value("${custom.rabbitmq.queues.userCreate}")
//    private String authCreateQueue;
//
//    @Bean
//    public TopicExchange topicExchange() {
//        return new TopicExchange(eventExchange, true, false);
//    }
//    @Bean
//    public Queue authCreateUserQueue() {
//        return new Queue(authCreateQueue, true);
//    }
//    @Bean
//    public Binding bindingAuthCreateUser(Queue authCreateUserQueue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(authCreateUserQueue)
//                .to(topicExchange)
//                .with("profile.user.create-account");
//    }
//}
