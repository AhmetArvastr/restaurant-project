package com.seyidahmetarvas.loggingservice.service;

import com.seyidahmetarvas.loggingservice.model.LogNotification;
import com.seyidahmetarvas.loggingservice.repository.LogNotificationRepository;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaConsumerService {

    private final LogNotificationRepository logNotificationRepository;
    Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    public KafkaConsumerService(LogNotificationRepository logNotificationRepository) {
        this.logNotificationRepository = logNotificationRepository;
    }

    @KafkaListener(topics = "errorLog", groupId = "log-consumer-group")
    public void errorConsumer(@Payload(required = false) String message) {
        try {
            if (message == null || message.isEmpty()) {
                logger.warn("Received an empty message from Kafka");
                return;
            }
            LogNotification errorLog = LogNotification.builder()
                    .message(message)
                    .date(LocalDateTime.now())
                    .build();
            logNotificationRepository.save(errorLog);
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
            throw e;
        }
    }

    @KafkaListener(topics = "userLog", groupId = "log-consumer-group")
    public void userConsumer(@Payload(required = false) String message) {
        try {
            if (message == null || message.isEmpty()) {
                logger.warn("Received an empty message from Kafka");
                return;
            }
            LogNotification errorLog = LogNotification.builder()
                    .message(message)
                    .date(LocalDateTime.now())
                    .build();
           logNotificationRepository.save(errorLog);
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
            throw e;
        }
    }
}
