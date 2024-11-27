package com.seyidahmetarvas.userservice.client;

import com.seyidahmetarvas.userservice.dto.response.RestaurantDto;
import com.seyidahmetarvas.userservice.exception.RestaurantNotFoundException;
import com.seyidahmetarvas.userservice.model.enums.Status;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name="RESTAURANT-SERVICE", path="/api/v1/restaurants")
public interface RestaurantClient {

    @GetMapping()
    ResponseEntity<List<RestaurantDto>> getAllRestaurants();

    @GetMapping ("/{id}")
    @CircuitBreaker(name = "getRestaurantByIdBreaker", fallbackMethod = "getRestaurantByIdFallback")
    ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable(name="id") String id);

    default ResponseEntity<RestaurantDto> getRestaurantByIdFallback(@PathVariable(name="id") String id) {
        BigDecimal a = BigDecimal.valueOf(3);
        throw new RestaurantNotFoundException("Book not found");
    }
}
