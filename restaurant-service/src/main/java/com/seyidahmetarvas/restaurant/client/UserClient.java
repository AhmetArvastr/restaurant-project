package com.seyidahmetarvas.restaurant.client;

import com.seyidahmetarvas.restaurant.common.RestResponse;
import com.seyidahmetarvas.restaurant.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "USER-SERVICE",path = "/api/v1/users")
public interface UserClient {
    @GetMapping("/{id}")
    ResponseEntity<RestResponse<UserDto>> getUserById(@PathVariable Long id);

    @GetMapping
    ResponseEntity<RestResponse<List<UserDto>>> getAllUsers();
}
