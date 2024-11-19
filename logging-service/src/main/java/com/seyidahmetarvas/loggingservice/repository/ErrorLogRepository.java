package com.seyidahmetarvas.loggingservice.repository;

import com.seyidahmetarvas.loggingservice.model.ErrorLog;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorLogRepository extends MongoRepository<ErrorLog, String> {
}
