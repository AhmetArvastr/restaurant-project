package com.seyidahmetarvas.restaurant.engine;

import com.seyidahmetarvas.restaurant.dto.response.RestaurantDto;
import com.seyidahmetarvas.restaurant.dto.response.RestaurantWithScoreDto;
import com.seyidahmetarvas.restaurant.util.RecommendationUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecommendationEngine {

    public static List<RestaurantWithScoreDto> getRecommendations(
            double userLat, double userLon, List<RestaurantDto> restaurants) {

        DecimalFormat df = new DecimalFormat("#.##");
        return restaurants.stream()
                .map(restaurant -> {
                    double restaurantLat = restaurant.latitude();
                    double restaurantLon = restaurant.longitude();
                    double distance = RecommendationUtils.calculateDistance(userLat, userLon, restaurantLat, restaurantLon);

                    if (distance > 10) {
                        return Optional.<RestaurantWithScoreDto>empty();
                    }

                    double score = (restaurant.restaurantRate() * 0.7) + (1 - (distance / 10)) * 0.3;

                    return Optional.of(new RestaurantWithScoreDto(
                            restaurant.id(),
                            restaurant.name(),
                            restaurant.address(),
                            restaurant.phone(),
                            restaurant.email(),
                            restaurant.description(),
                            restaurant.website(),
                            restaurant.workingHours(),
                            restaurant.latitude(),
                            restaurant.longitude(),
                            restaurant.restaurantRate(),
                            score,
                            Double.parseDouble(df.format(distance)),
                            restaurant.status()
                    ));
                })
                .flatMap(Optional::stream)
                .sorted(Comparator.comparingDouble(RestaurantWithScoreDto::score).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }
}
