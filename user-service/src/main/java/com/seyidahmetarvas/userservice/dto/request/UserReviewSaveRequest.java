package com.seyidahmetarvas.userservice.dto.request;

import java.math.BigDecimal;

public record UserReviewSaveRequest(
        String restaurantId,
        String comment,
        BigDecimal userRate,
        Long userId
) {
}
