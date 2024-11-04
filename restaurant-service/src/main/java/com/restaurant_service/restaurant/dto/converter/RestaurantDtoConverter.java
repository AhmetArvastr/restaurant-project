package com.restaurant_service.restaurant.dto.converter;

import com.restaurant_service.restaurant.dto.requests.RestaurantUpdateRequest;
import com.restaurant_service.restaurant.dto.response.RestaurantDto;
import com.restaurant_service.restaurant.model.Restaurant;
import com.restaurant_service.restaurant.model.enums.Status;

import java.math.BigDecimal;

public class RestaurantDtoConverter {
    public RestaurantDto convert(Restaurant restaurant) {
        return new RestaurantDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhone(),
                restaurant.getEmail(),
                restaurant.getDescription(),
                restaurant.getWebsite(),
                restaurant.getWorkingHours(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                restaurant.getRestaurantRate(),
                restaurant.getStatus()
        );
    }

    public Restaurant convertToRestaurant(Restaurant restaurant, RestaurantUpdateRequest request) {
        restaurant.setName(request.name());
        restaurant.setAddress(request.address());
        restaurant.setPhone(request.phone());
        restaurant.setEmail(request.email());
        restaurant.setDescription(request.description());
        restaurant.setWebsite(request.website());
        restaurant.setWorkingHours(request.workingHours());
        restaurant.setLatitude(request.latitude());
        restaurant.setLongitude(request.longitude());
        restaurant.setRestaurantRate(request.restaurantRate());
        restaurant.setStatus(request.status());
        return restaurant;
    }
}
