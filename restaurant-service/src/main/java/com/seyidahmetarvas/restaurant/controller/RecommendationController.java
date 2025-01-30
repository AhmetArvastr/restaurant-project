package com.seyidahmetarvas.restaurant.controller;

import com.seyidahmetarvas.restaurant.common.RestResponse;
import com.seyidahmetarvas.restaurant.dto.response.RecommendationDto;
import com.seyidahmetarvas.restaurant.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants/recommendations")
public class RecommendationController {
    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Operation(summary = "GET request for restaurant recommendations by user id", description = "Returns restaurant recommendations for a user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<RestResponse<RecommendationDto>> getRecommendationByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(RestResponse.of(recommendationService.getRecommendationByUserId(userId), "Restaurant recommendations listed by user id"));
    }

    @Operation(summary = "GET request for restaurant recommendations with users", description = "Returns restaurant recommendations for all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<RecommendationDto>>> getRecommendationsWithUsers() {
        return ResponseEntity.ok(RestResponse.of(recommendationService.getAllRecommendationsWithAllUsers(), "Restaurant recommendations listed with users"));
    }
}
