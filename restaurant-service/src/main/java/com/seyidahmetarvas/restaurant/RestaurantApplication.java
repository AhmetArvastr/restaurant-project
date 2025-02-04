package com.seyidahmetarvas.restaurant;

import com.seyidahmetarvas.restaurant.model.Restaurant;
import com.seyidahmetarvas.restaurant.repository.RestaurantRepository;
import com.seyidahmetarvas.restaurant.util.JsonUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class RestaurantApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(RestaurantRepository restaurantRepository) {
		return args -> {
			if (restaurantRepository.count() == 0) {
				List<Restaurant> users = JsonUtil.readRestaurantsFromJson();
				restaurantRepository.saveAll(users);
			}
		};
	}
}
