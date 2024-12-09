package com.seyidahmetarvas.userservice.exception;

import java.time.LocalDateTime;

public record ExceptionMessage(
        String timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
