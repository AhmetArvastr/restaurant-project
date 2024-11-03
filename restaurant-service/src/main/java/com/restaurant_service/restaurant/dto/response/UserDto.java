package com.restaurant_service.restaurant.dto.response;

public record UserDto(
        Long id,
        String name,
        String surname,
        String email,
        double latitude,
        double longitude
) {
}
