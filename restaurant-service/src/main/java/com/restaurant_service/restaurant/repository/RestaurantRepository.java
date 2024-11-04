package com.restaurant_service.restaurant.repository;

import com.restaurant_service.restaurant.model.Restaurant;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends SolrCrudRepository<Restaurant, String> {
    Optional<Restaurant> findById(String id);

    Restaurant save(Restaurant restaurant);

    List<Restaurant> findAll();

    void deleteById(Restaurant restaurant);
}
