package com.seyidahmetarvas.userservice.exception;

import java.time.LocalDateTime;

public record ExceptionMessage(
        LocalDateTime date,
        int status,
        String error,
        String message,
        String path
) {
}
