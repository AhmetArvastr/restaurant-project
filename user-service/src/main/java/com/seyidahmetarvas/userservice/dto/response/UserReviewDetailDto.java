package com.seyidahmetarvas.userservice.dto.response;

import java.math.BigDecimal;

public record UserReviewDetailDto (
        Long id,
        String restaurantId,
        String restaurantName,
        String comment,
        BigDecimal userRate,
        UserDto user
) {
}
