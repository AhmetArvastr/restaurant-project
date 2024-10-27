package com.seyidahmetarvas.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ItemNotActiveException extends RuntimeException{
    public ItemNotActiveException(String message) {
        super(message);
    }
}
