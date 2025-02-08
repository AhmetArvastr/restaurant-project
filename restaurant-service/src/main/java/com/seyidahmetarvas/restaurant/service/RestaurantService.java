package com.seyidahmetarvas.restaurant.service;

import com.seyidahmetarvas.restaurant.dto.converter.RestaurantDtoConverter;
import com.seyidahmetarvas.restaurant.dto.requests.RestaurantSaveRequest;
import com.seyidahmetarvas.restaurant.dto.requests.RestaurantUpdateRequest;
import com.seyidahmetarvas.restaurant.dto.response.RestaurantDto;
import com.seyidahmetarvas.restaurant.exception.RestaurantNotFoundException;
import com.seyidahmetarvas.restaurant.model.Restaurant;
import com.seyidahmetarvas.restaurant.repository.RestaurantRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantDtoConverter converter;
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantDtoConverter converter, RestaurantRepository restaurantRepository) {
        this.converter = converter;
        this.restaurantRepository = restaurantRepository;
    }

    protected Restaurant findRestraurantById(String id){
        return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException("Restaurant could not found by id: " + id));
    }

    public RestaurantDto getRestraurantById(String id){
        return converter.convert(findRestraurantById(id));
    }

    public RestaurantDto createRestaurant(RestaurantSaveRequest request) {
        Restaurant restaurant = new Restaurant(
                request.name(),
                request.address(),
                request.phone(),
                request.email(),
                request.website(),
                request.description(),
                request.workingHours(),
                request.latitude(),
                request.longitude(),
                request.restaurantRate(),
                request.status()
        );
        return converter.convert(restaurantRepository.save(restaurant));
    }

    public RestaurantDto updateRestaurant(RestaurantUpdateRequest request) {
        return converter.convert(restaurantRepository.save(
                converter.convertToRestaurant(findRestraurantById(request.id()),request)));
    }

    public List<RestaurantDto> getAllRestaurants() {
        return converter.convert(restaurantRepository.findAll());
    }

    public void deleteRestaurantById(String id) {
        restaurantRepository.delete(findRestraurantById(id));
    }

    public List<RestaurantDto> searchRestaurantByRestaurantRate(Double restaurantRate) {
        return converter.convert(restaurantRepository.searchByRestaurantRate(restaurantRate));
    }

    public List<RestaurantDto> autoSuggestRestaurantsByName(String name) {
        return restaurantRepository.autocompleteSearch(name)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
