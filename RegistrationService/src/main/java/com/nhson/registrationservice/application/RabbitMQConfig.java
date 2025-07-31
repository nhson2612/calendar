package com.nhson.registrationservice.application;

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
    private String exchangeName;
    public static final String EMAIL_QUEUE = "notification.email.queue";
    public static final String TELEGRAM_QUEUE = "notification.telegram.queue";

    public static final String EMAIL_ROUTING_KEY = "notification.email";
    public static final String TELEGRAM_ROUTING_KEY = "notification.telegram";
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchangeName, true, false);
    }


    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE);
    }

    @Bean
    public Queue telegramQueue() {
        return new Queue(TELEGRAM_QUEUE);
    }

    @Bean
    public Binding emailBinding(Queue emailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(emailQueue).to(topicExchange).with(EMAIL_ROUTING_KEY);
    }

    @Bean
    public Binding telegramBinding(Queue telegramQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(telegramQueue).to(topicExchange).with(TELEGRAM_ROUTING_KEY);
    }
}
