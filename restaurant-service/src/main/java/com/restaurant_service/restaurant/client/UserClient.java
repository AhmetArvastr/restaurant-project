package com.restaurant_service.restaurant.client;

import com.restaurant_service.restaurant.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE",path = "/api/v1/users")
public interface UserClient {
    @GetMapping("/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable Long id);
}
