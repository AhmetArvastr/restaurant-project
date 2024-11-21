package com.seyidahmetarvas.userservice.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyidahmetarvas.userservice.exception.ExceptionMessage;
import com.seyidahmetarvas.userservice.exception.RestaurantNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;
        try (InputStream body = response.body().asInputStream()){
            JsonNode jsonNode = objectMapper.readTree(IOUtils.toString(body, StandardCharsets.UTF_8));
            message = new ExceptionMessage(
                    Optional.ofNullable(jsonNode.get("timestamp"))
                            .map(JsonNode::asText)
                            .orElse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))),
                    response.status(),
                    Objects.requireNonNull(HttpStatus.resolve(response.status())).getReasonPhrase(),
                    Optional.ofNullable(jsonNode.get("message"))
                            .map(JsonNode::asText)
                            .orElse(jsonNode.toString()),
                    response.request().url());
        } catch (IOException exception) {
            return new Exception(exception.getMessage());
        }
        switch (response.status()) {
            case 404:
                throw new RestaurantNotFoundException(message);
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
