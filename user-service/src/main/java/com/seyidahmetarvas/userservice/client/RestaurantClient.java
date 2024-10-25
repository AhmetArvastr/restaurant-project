package com.seyidahmetarvas.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="RESTAURANT-SERVICE", path="/api/v1/restaurants")
public class RestaurantClient {
}
