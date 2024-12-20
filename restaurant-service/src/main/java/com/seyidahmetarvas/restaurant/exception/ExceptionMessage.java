package com.seyidahmetarvas.restaurant.exception;

public record ExceptionMessage(
        String timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
