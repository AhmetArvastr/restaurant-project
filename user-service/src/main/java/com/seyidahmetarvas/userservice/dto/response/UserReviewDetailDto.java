package com.seyidahmetarvas.userservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserReviewDetailDto (
        LocalDateTime createdAt,
        String restaurantId,
        String restaurantName,
        String comment,
        BigDecimal userRate,
        UserDto user
) {
}
