package com.seyidahmetarvas.userservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void userMessage(String userName,String message) {
        kafkaTemplate.send(
                "userLog",
                "Dear User "+userName+" \n Your review request was successful. Your review has been"+message);
    }

    public void errorMessage(String message,String path) {
        kafkaTemplate.send(
                "errorLog",
                "message: "+message+" path: "+path);
    }
}
