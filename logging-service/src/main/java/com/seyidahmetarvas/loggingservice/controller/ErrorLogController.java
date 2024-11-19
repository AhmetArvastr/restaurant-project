package com.seyidahmetarvas.loggingservice.controller;

import com.seyidahmetarvas.loggingservice.dto.ErrorLogDto;
import com.seyidahmetarvas.loggingservice.service.ErrorLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/error-logs")
public class ErrorLogController {

    private final ErrorLogService errorLogService;

    public ErrorLogController(ErrorLogService errorLogService) {
        this.errorLogService = errorLogService;
    }

    @GetMapping
    public List<ErrorLogDto> findAll() {
        return errorLogService.findAll();
    }
}
