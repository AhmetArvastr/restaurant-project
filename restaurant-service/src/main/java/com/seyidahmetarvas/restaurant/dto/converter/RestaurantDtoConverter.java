package com.seyidahmetarvas.restaurant.dto.converter;

import com.seyidahmetarvas.restaurant.dto.requests.RestaurantUpdateRequest;
import com.seyidahmetarvas.restaurant.dto.response.RestaurantDto;
import com.seyidahmetarvas.restaurant.model.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantDtoConverter {
    public RestaurantDto convert(Restaurant restaurant) {
        return new RestaurantDto(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getPhone(),
                restaurant.getEmail(),
                restaurant.getDescription(),
                restaurant.getWebsite(),
                restaurant.getWorkingHours(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                restaurant.getRestaurantRate(),
                restaurant.getStatus()
        );
    }

    public List<RestaurantDto> convert(Iterable<Restaurant> restaurants) {
        List<RestaurantDto> restaurantDtos = new ArrayList<>();

        for (Restaurant restaurant : restaurants) {
            RestaurantDto restaurantDto = new RestaurantDto(
                    restaurant.getId(),
                    restaurant.getName(),
                    restaurant.getAddress(),
                    restaurant.getPhone(),
                    restaurant.getEmail(),
                    restaurant.getDescription(),
                    restaurant.getWebsite(),
                    restaurant.getWorkingHours(),
                    restaurant.getLatitude(),
                    restaurant.getLongitude(),
                    restaurant.getRestaurantRate(),
                    restaurant.getStatus()
            );
            restaurantDtos.add(restaurantDto);
        }

        return restaurantDtos;
    }

    public Restaurant convertToRestaurant(Restaurant restaurant, RestaurantUpdateRequest request) {
        restaurant.setName(request.name());
        restaurant.setAddress(request.address());
        restaurant.setPhone(request.phone());
        restaurant.setEmail(request.email());
        restaurant.setDescription(request.description());
        restaurant.setWebsite(request.website());
        restaurant.setWorkingHours(request.workingHours());
        restaurant.setLatitude(request.latitude());
        restaurant.setLongitude(request.longitude());
        restaurant.setRestaurantRate(request.restaurantRate());
        restaurant.setStatus(request.status());
        return restaurant;
    }
}
