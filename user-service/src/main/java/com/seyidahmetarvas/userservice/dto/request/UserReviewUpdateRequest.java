package com.seyidahmetarvas.userservice.dto.request;

import com.seyidahmetarvas.userservice.model.User;

import java.math.BigDecimal;

public record UserReviewUpdateRequest (
        Long id,
        String restaurantId,
        String comment,
        BigDecimal userRate,
        User user
){
}
