package com.seyidahmetarvas.restaurant.controller;

import com.seyidahmetarvas.restaurant.common.RestResponse;
import com.seyidahmetarvas.restaurant.dto.requests.RestaurantSaveRequest;
import com.seyidahmetarvas.restaurant.dto.requests.RestaurantUpdateRequest;
import com.seyidahmetarvas.restaurant.dto.response.RestaurantDto;
import com.seyidahmetarvas.restaurant.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(summary = "GET request for all restaurants", description = "Returns all restaurants in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<RestaurantDto>>> getAllRestaurants() {
        return ResponseEntity.ok(RestResponse.of(restaurantService.getAllRestaurants(), "Restaurants listed successfully"));
    }

    @Operation(summary = "GET request for restaurant by id", description = "Returns a restaurant by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<RestaurantDto>> getRestaurantById(@PathVariable String id) {
        return ResponseEntity.ok(RestResponse.of(restaurantService.getRestraurantById(id), "Restaurant listed by id"));
    }

    @Operation(summary = "POST request to save a restaurant", description = "Saves a restaurant to database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request<br>- E-mail already exists"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<RestResponse<RestaurantDto>> saveRestaurant(@RequestBody RestaurantSaveRequest request) {
        return ResponseEntity.ok(RestResponse.of(restaurantService.createRestaurant(request), "Restaurant saved successfully"));
    }

    @Operation(summary = "PUT request to update a restaurant", description = "Updates a restaurant in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request<br>- Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping
    public ResponseEntity<RestResponse<RestaurantDto>> updateRestaurant(@RequestBody RestaurantUpdateRequest request) {
        return ResponseEntity.ok(RestResponse.of(restaurantService.updateRestaurant(request), "Restaurant updated successfully"));
    }

    @Operation(summary = "DELETE request to delete a restaurant", description = "Deletes a restaurant from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request<br>- Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<?>> deleteRestaurant(@PathVariable String id) {
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.ok(RestResponse.empty("Restaurant is deleted successfully"));
    }
}
