package com.seyidahmetarvas.loggingservice.service;

import com.seyidahmetarvas.loggingservice.dto.ErrorLogDto;
import com.seyidahmetarvas.loggingservice.repository.LogNotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ErrorLogService {

    private final LogNotificationRepository logNotificationRepository;

    public ErrorLogService(LogNotificationRepository logNotificationRepository) {
        this.logNotificationRepository = logNotificationRepository;
    }

    public List<ErrorLogDto> findAll() {
        return logNotificationRepository.findAll()
                .stream()
                .map(logger -> new ErrorLogDto(
                        logger.getMessage(),
                        logger.getDate()
                )).collect(Collectors.toList());
    }
}
