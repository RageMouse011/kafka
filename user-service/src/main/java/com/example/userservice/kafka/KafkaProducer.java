package com.example.userservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String USER_TOPIC = "user-topic";

    public void sendUserTopic(String message) {
        kafkaTemplate.send(USER_TOPIC, message);
        log.info("Message {}, has been successfully sent to the topic: {}", message, USER_TOPIC);
    }

}
