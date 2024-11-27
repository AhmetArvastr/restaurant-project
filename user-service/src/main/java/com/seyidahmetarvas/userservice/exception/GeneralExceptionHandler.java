package com.seyidahmetarvas.userservice.exception;

import com.seyidahmetarvas.userservice.common.base.RestResponse;
import com.seyidahmetarvas.userservice.service.KafkaProducerService;

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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handle(UserNotFoundException exception, WebRequest request) {
        kafka.errorMessage(
                exception.getMessage(), request.getDescription(false).replace("uri",""));

        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<?> handle(UserNotActiveException exception, WebRequest request) {
        kafka.errorMessage(
                exception.getMessage(), request.getDescription(false).replace("uri",""));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserReviewNotFoundException.class)
    public ResponseEntity<?> handle(UserReviewNotFoundException exception, WebRequest request) {
        kafka.errorMessage(
                exception.getMessage(), request.getDescription(false).replace("uri",""));
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<?> handle(RestaurantNotFoundException exception, WebRequest request) {
        kafka.errorMessage(
                exception.getMessage(), request.getDescription(false).replace("uri",""));
        return new ResponseEntity<>(exception.getExceptionMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception exception, WebRequest request) {
        kafka.errorMessage(
                exception.getMessage(), request.getDescription(false).replace("uri",""));
        return ResponseEntity.badRequest().body(RestResponse.error(exception.getMessage(), "Unknown error"));
    }
}
