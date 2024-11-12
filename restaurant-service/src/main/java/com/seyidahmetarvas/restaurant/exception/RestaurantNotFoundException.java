package com.seyidahmetarvas.restaurant.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends RuntimeException {
    private ExceptionMessage exceptionMessage;

    public RestaurantNotFoundException(String message){
        super(message);
    }

    public RestaurantNotFoundException(ExceptionMessage message) {
        this.exceptionMessage = message;
    }

    public RestaurantNotFoundException(String message, ExceptionMessage exceptionMessage) {
        super(message);
        this.exceptionMessage = exceptionMessage;
    }

    public ExceptionMessage getExceptionMessage() {
        return exceptionMessage;
    }

    public ExceptionMessage setExceptionMessage(ExceptionMessage exceptionMessage) {
        return this.exceptionMessage = exceptionMessage;
    }

}
