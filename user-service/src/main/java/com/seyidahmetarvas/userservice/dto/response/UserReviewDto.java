package com.seyidahmetarvas.userservice.dto.response;

import java.math.BigDecimal;

public record UserReviewDto (
        Long id,
        UserDto user,
        String restaurantId,
        String restaurantName,
        String comment,
        BigDecimal userRate
){
}
