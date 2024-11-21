package com.seyidahmetarvas.userservice.dto.request;

import java.math.BigDecimal;

public record UserReviewUpdateRequest (
        Long id,
        String restaurantId,
        String comment,
        BigDecimal userRate
){
}
