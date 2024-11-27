package com.seyidahmetarvas.loggingservice.repository;

import com.seyidahmetarvas.loggingservice.model.LogNotification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogNotificationRepository extends MongoRepository<LogNotification, String> {
}