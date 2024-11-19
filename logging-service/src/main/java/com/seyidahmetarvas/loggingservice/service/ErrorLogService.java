package com.seyidahmetarvas.loggingservice.service;

import com.seyidahmetarvas.loggingservice.dto.ErrorLogDto;
import com.seyidahmetarvas.loggingservice.repository.ErrorLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ErrorLogService {

    private final ErrorLogRepository errorLogRepository;

    public ErrorLogService(ErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    public List<ErrorLogDto> findAll() {
        return errorLogRepository.findAll()
                .stream()
                .map(errorlog -> new ErrorLogDto(
                        errorlog.getMessage(),
                        errorlog.getDate()
                )).collect(Collectors.toList());
    }
}
