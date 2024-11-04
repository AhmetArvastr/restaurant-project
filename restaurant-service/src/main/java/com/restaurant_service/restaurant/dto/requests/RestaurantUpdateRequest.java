package com.restaurant_service.restaurant.dto.requests;

import com.restaurant_service.restaurant.model.enums.Status;

import java.math.BigDecimal;

public record RestaurantUpdateRequest(
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
