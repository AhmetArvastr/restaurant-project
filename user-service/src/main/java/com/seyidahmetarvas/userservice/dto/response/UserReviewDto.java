package com.seyidahmetarvas.userservice.dto.response;

import java.math.BigDecimal;

public record UserReviewDto (
        Long id,
        String restaurantId,
        String comment,
        BigDecimal userRate,
        UserDto user
){
}
