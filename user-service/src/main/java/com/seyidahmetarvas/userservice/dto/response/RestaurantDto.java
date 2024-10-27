package com.seyidahmetarvas.userservice.dto.response;

import com.seyidahmetarvas.userservice.model.enums.Status;

import java.math.BigDecimal;

public record RestaurantDto(
        String id,
        String name,
        String address,
        String phone,
        String email,
        String description,
        String website,
        String workingHours,
        BigDecimal rate,
        Status status
) {
}
