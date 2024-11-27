package com.seyidahmetarvas.restaurant.exception;

import com.seyidahmetarvas.restaurant.service.KafkaProducerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GeneralExceptionHandler {

    private final KafkaProducerService kafka;

    public GeneralExceptionHandler(KafkaProducerService kafka) {
        this.kafka = kafka;
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<?> handle(RestaurantNotFoundException exception, WebRequest request) {
        kafka.errorMessage(
                exception.getMessage(), request.getDescription(false).replace("uri",""));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handle(UserNotFoundException exception, WebRequest request) {
        kafka.errorMessage(
                exception.getMessage(), request.getDescription(false).replace("uri",""));
        return new ResponseEntity<>(exception.getExceptionMessage(), HttpStatus.NOT_FOUND);
    }
}
