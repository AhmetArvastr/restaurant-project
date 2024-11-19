package com.seyidahmetarvas.userservice.client;

import com.seyidahmetarvas.userservice.common.base.RestResponse;
import com.seyidahmetarvas.userservice.dto.response.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="RESTAURANT-SERVICE", path="/api/v1/restaurants")
public interface RestaurantClient {

    @GetMapping()
    ResponseEntity<List<RestaurantDto>> getAllRestaurants();

    @GetMapping ("/{id}")
    ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable(name="id") String id);
}
