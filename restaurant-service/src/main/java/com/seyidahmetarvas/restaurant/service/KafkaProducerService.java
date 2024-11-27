package com.seyidahmetarvas.restaurant.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void errorMessage(String message,String path) {
        kafkaTemplate.send(
                "errorLog",
                "message: "+message+" path: "+path);
    }
}
