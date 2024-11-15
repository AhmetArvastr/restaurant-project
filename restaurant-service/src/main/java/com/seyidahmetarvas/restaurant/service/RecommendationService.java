package com.seyidahmetarvas.restaurant.service;

import com.seyidahmetarvas.restaurant.client.UserClient;
import com.seyidahmetarvas.restaurant.dto.response.UserDto;
import com.seyidahmetarvas.restaurant.dto.response.RecommendationDto;
import com.seyidahmetarvas.restaurant.dto.response.RestaurantDto;
import com.seyidahmetarvas.restaurant.dto.response.RestaurantWithScoreDto;
import com.seyidahmetarvas.restaurant.engine.RecommendationEngine;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final RestaurantService restaurantService;
    private final UserClient userClient;

    public RecommendationService(RestaurantService restaurantService, UserClient userClient) {
        this.restaurantService = restaurantService;
        this.userClient = userClient;
    }

    public List<RecommendationDto> getAllRecommendationsWithAllUsers() {
        List<UserDto> users = Optional.ofNullable(userClient.getAllUsers().getBody())
                .orElseThrow(() -> new IllegalArgumentException("No users found"));

        List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();

        return users.stream()
                .map(user -> {
                    double userLat = user.latitude();
                    double userLon = user.longitude();

                    List<RestaurantWithScoreDto> userRecommendations = RecommendationEngine.getRecommendations(userLat, userLon, restaurants);

                    return new RecommendationDto(user, userRecommendations);
                })
                .collect(Collectors.toList());
    }

    public RecommendationDto getRecommendationByUserId(Long userId) {
        UserDto userDto = userClient.getUserById(userId).getBody();

        double userLat = userDto.latitude();
        double userLon = userDto.longitude();

        List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();

        List<RestaurantWithScoreDto> restaurantWithScoreDto = RecommendationEngine.getRecommendations(userLat, userLon, restaurants);

        RecommendationDto recommendationDto = new RecommendationDto(userDto, restaurantWithScoreDto);

        return recommendationDto;
    }
}
