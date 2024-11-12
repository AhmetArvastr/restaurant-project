package com.seyidahmetarvas.restaurant.repository;

import com.seyidahmetarvas.restaurant.model.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, String> {
}
