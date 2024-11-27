package com.seyidahmetarvas.loggingservice.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "errorlogdoc")
public class LogNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private final LocalDateTime date;
    private final String message;


    private LogNotification(Builder builder) {
        this.date = builder.date;
        this.message = builder.message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private LocalDateTime date;
        private String message;

        public Builder date (LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Builder message (String message) {
            this.message = message;
            return this;
        }

        public LogNotification build() {
            return new LogNotification(this);
        }
    }
}
