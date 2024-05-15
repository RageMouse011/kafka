package com.example.taskservice.kafka;

import com.example.taskservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final TaskService taskService;
    private final String USER_TOPIC = "user-topic";
    private final String USER_CONSUMER_GROUP_ID = "user-consumer-group-id";

    @KafkaListener(topics = USER_TOPIC, groupId = USER_CONSUMER_GROUP_ID)
    public void consumeMessage(String message) {
        log.info("New user: {}", message);
        taskService.addDefaultTaskToNewUser(message);
    }
}