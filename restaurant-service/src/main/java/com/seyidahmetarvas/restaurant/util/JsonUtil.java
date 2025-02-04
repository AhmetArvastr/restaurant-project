package com.seyidahmetarvas.restaurant.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seyidahmetarvas.restaurant.model.Restaurant;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonUtil {
    public static List<Restaurant> readRestaurantsFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("data/restaurants.json");
        InputStream inputStream = resource.getInputStream();
        return objectMapper.readValue(inputStream, new TypeReference<List<Restaurant>>() {
        });
    }
}
