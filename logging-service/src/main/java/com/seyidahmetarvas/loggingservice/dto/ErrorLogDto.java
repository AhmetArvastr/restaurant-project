package com.seyidahmetarvas.loggingservice.dto;

import java.time.LocalDateTime;

public record ErrorLogDto(
        String message,
        LocalDateTime date
) {
}
