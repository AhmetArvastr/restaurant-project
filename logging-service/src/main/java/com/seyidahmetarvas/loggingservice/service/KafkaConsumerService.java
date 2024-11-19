package com.seyidahmetarvas.loggingservice.service;

import com.seyidahmetarvas.loggingservice.model.ErrorLog;
import com.seyidahmetarvas.loggingservice.repository.ErrorLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaConsumerService {
    private final ErrorLogRepository errorLogRepository;

    Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    public KafkaConsumerService(ErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    @KafkaListener(topics = "errorLog", groupId = "log-consumer-group")
    public void consume(@Payload(required = false) String message) {
        try {
            if (message == null || message.isEmpty()) {
                logger.warn("Received an empty message from Kafka");
                return;
            }
            ErrorLog errorLog = ErrorLog.builder()
                    .message(message)
                    .date(LocalDateTime.now())
                    .build();
            errorLogRepository.save(errorLog);
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
            throw e;
        }
    }
}
