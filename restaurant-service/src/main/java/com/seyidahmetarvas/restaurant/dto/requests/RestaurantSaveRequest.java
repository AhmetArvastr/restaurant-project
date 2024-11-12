package com.seyidahmetarvas.restaurant.dto.requests;

import com.seyidahmetarvas.restaurant.model.enums.Status;

public record RestaurantSaveRequest(
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
