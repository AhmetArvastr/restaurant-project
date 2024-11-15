package com.seyidahmetarvas.restaurant.dto.response;

import java.util.List;

public record RecommendationDto(
        UserDto user,
        List<RestaurantWithScoreDto> restaurantList
) {
}
