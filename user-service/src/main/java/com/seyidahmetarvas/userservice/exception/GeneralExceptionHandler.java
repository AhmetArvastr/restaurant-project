package com.seyidahmetarvas.userservice.exception;

import com.seyidahmetarvas.userservice.common.base.RestResponse;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handle(UserNotFoundException exception, WebRequest request) {
        ExceptionMessage message = new ExceptionMessage(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                HttpStatus.NOT_FOUND.value(),
                Objects.requireNonNull(HttpStatus.resolve(HttpStatus.NOT_FOUND.value())).getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false).replace("uri",""));
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotActiveException.class)
    public ResponseEntity<?> handle(UserNotActiveException exception, WebRequest request) {
        ExceptionMessage message = new ExceptionMessage(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                HttpStatus.FORBIDDEN.value(),
                Objects.requireNonNull(HttpStatus.resolve(HttpStatus.FORBIDDEN.value())).getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false).replace("uri",""));
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserReviewNotFoundException.class)
    public ResponseEntity<?> handle(UserReviewNotFoundException exception, WebRequest request) {
        ExceptionMessage message = new ExceptionMessage(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                HttpStatus.NOT_FOUND.value(),
                Objects.requireNonNull(HttpStatus.resolve(HttpStatus.NOT_FOUND.value())).getReasonPhrase(),
                exception.getMessage(),
                request.getDescription(false).replace("uri",""));
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handle(RestaurantNotFoundException exception) {
        return new ResponseEntity<>(exception.getExceptionMessage(),
                Objects.requireNonNull(HttpStatus.resolve(exception.getExceptionMessage().status())));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        ExceptionMessage message = new ExceptionMessage(
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                HttpStatus.NO_CONTENT.value(),
                Objects.requireNonNull(HttpStatus.resolve(HttpStatus.NO_CONTENT.value())).getReasonPhrase(),
                ex.getMessage(),
                request.getDescription(false).replace("uri",""));
        return ResponseEntity.badRequest().body(RestResponse.error(message, "Unknown error"));
    }
}
