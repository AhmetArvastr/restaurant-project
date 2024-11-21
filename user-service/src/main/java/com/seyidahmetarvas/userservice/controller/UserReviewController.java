package com.seyidahmetarvas.userservice.controller;

import com.seyidahmetarvas.userservice.common.base.RestResponse;
import com.seyidahmetarvas.userservice.dto.request.UserReviewSaveRequest;
import com.seyidahmetarvas.userservice.dto.request.UserReviewUpdateRequest;
import com.seyidahmetarvas.userservice.dto.response.UserReviewDetailDto;
import com.seyidahmetarvas.userservice.dto.response.UserReviewDto;
import com.seyidahmetarvas.userservice.service.UserReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/users/reviews")
public class UserReviewController {
    private final UserReviewService userReviewService;

    public UserReviewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }

    @Operation(summary = "GET request for user review by id", description = "Returns a user review by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-User review not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserReviewDto>> getUserReviewById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(
                RestResponse.of(userReviewService.getUserReviewById(id),
                        "User review listed by id successfully"));
    }

    @Operation(summary = "GET request for user review detail by id", description = "Returns a user review detail by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-User review not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/detail/{id}")
    public ResponseEntity<RestResponse<UserReviewDetailDto>> getUserReviewDetailById(@PathVariable @Positive Long id) {
        return ResponseEntity.ok(
                RestResponse.of(
                        userReviewService.getUserReviewDetailById(id), "User review detail listed by id successfully"));
    }

    @Operation(summary = "POST request to create user review", description = "Creates a user review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request<br>-Restaurant not found<br>-User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PostMapping
    public ResponseEntity<RestResponse<UserReviewDetailDto>> createUserReview(@Valid @RequestBody
                                                                                  UserReviewSaveRequest request) {
        return ResponseEntity.ok(
                RestResponse.of(
                        userReviewService.createUserReview(request), "User review created successfully"));
    }

    @Operation(summary = "GET request for all user reviews", description = "Returns all user reviews.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping
    public ResponseEntity<RestResponse<List<UserReviewDetailDto>>> getAllUserReviews() {
        return ResponseEntity.ok(
                RestResponse.of(
                        userReviewService.getAllUserReviews(), "All user reviews listed successfully"));
    }

    @Operation(summary = "GET request for user reviews by user id", description = "Returns user reviews by user id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<RestResponse<List<UserReviewDetailDto>>> getUserReviewsByUserId(@PathVariable @Positive
                                                                                              Long userId) {
        return ResponseEntity.ok(
                RestResponse.of(
                        userReviewService.getUserReviewsByUserId(userId),
                        "User reviews listed by userId successfully"));
    }

    @Operation(summary = "GET request for user reviews by restaurant id", description = "Returns user reviews by restaurant id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "Not Found<br>-Restaurant not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<RestResponse<List<UserReviewDetailDto>>> getUserReviewsByRestaurantId(@PathVariable
                                                                                                    String restaurantId) {
        return ResponseEntity.ok(
                RestResponse.of(
                        userReviewService.getUserReviewsByRestaurantId(restaurantId),
                        "User reviews listed by restaurantId successfully"));
    }

    @Operation(summary = "PUT request for user review", description = "Edits a user review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request<br>-User review not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @PutMapping
    public ResponseEntity<RestResponse<UserReviewDetailDto>> editUserReview(@Valid @RequestBody
                                                                                UserReviewUpdateRequest request) {
        return ResponseEntity.ok(
                RestResponse.of(userReviewService.editUserReview(request), "Updated user reviews successfully"));
    }

    @Operation(summary = "DELETE request to delete a user review", description = "Deletes a user review.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "Bad Request<br>-User review not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<RestResponse<String>> deleteUserReview(@PathVariable @Positive Long id) {
        userReviewService.deleteUserReview(id);
        return ResponseEntity.ok(RestResponse.empty("User review deleted successfully"));
    }
}
