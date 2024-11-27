package com.seyidahmetarvas.restaurant.client;

import com.seyidahmetarvas.restaurant.exception.ExceptionMessage;
import com.seyidahmetarvas.restaurant.exception.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;
        try (InputStream body = response.body().asInputStream()){
            message = new ExceptionMessage(
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    response.status(),
                    Objects.requireNonNull(HttpStatus.resolve(response.status())).getReasonPhrase(),
                    IOUtils.toString(body, StandardCharsets.UTF_8),
                    response.request().url());
        } catch (IOException exception) {
            return new Exception(exception.getMessage());
        }
        switch (response.status()) {
            case 404:
                throw new UserNotFoundException(message);
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
