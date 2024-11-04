package com.restaurant_service.restaurant.dto.response;

import com.restaurant_service.restaurant.model.enums.Status;

public record RestaurantDto(
        String id,
        String name,
        String address,
        String phone,
        String email,
        String description,
        String website,
        String workingHours,
        double latitude,
        double longitude,
        Double restaurantRate,
        Status status
) {
}
