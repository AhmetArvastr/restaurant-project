package com.restaurant_service.restaurant.service;

import com.restaurant_service.restaurant.dto.converter.RestaurantDtoConverter;
import com.restaurant_service.restaurant.dto.requests.RestaurantSaveRequest;
import com.restaurant_service.restaurant.dto.requests.RestaurantUpdateRequest;
import com.restaurant_service.restaurant.dto.response.RestaurantDto;
import com.restaurant_service.restaurant.exception.RestaurantNotFoundException;
import com.restaurant_service.restaurant.model.Restaurant;
import com.restaurant_service.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;
    private final RestaurantDtoConverter converter;
    public RestaurantService(RestaurantRepository repository, RestaurantDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    protected Restaurant findRestraurantById(String id){
        return repository.findById(id).orElseThrow(()-> new RestaurantNotFoundException("Restaurant not found by id "+id));
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
        return converter.convert(repository.save(restaurant));
    }

    public RestaurantDto updateRestaurant(RestaurantUpdateRequest request) {
        return converter.convert(repository.save(
                converter.convertToRestaurant(findRestraurantById(request.id()),request)));
    }

    public List<RestaurantDto> getAllRestaurants() {
        return repository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public void deleteRestaurant(String id) {
        repository.deleteById(findRestraurantById(id));
    }
}
