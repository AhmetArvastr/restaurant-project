package com.seyidahmetarvas.restaurant.repository;

import com.seyidahmetarvas.restaurant.model.Restaurant;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {
    @Query("{\"bool\": {\"filter\": {\"term\": {\"restaurantRate\": ?0}}}}")
    List<Restaurant> searchByRestaurantRate(double restaurantRate);

    @Query("{\"bool\": {\"filter\": {\"match\": {\"name\": \"?0\"}}}}")
    List<Restaurant> autocompleteSearch(String input);
}
