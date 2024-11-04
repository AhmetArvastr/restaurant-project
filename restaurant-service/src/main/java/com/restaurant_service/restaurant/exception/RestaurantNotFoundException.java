package com.restaurant_service.restaurant.exception;

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
