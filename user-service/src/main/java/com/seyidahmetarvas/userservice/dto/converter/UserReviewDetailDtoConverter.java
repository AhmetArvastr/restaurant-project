package com.seyidahmetarvas.userservice.dto.converter;

import com.seyidahmetarvas.userservice.dto.response.RestaurantDto;
import com.seyidahmetarvas.userservice.dto.response.UserReviewDetailDto;
import com.seyidahmetarvas.userservice.model.UserReview;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UserReviewDetailDtoConverter {
    private final UserDtoConverter converter;

    public UserReviewDetailDtoConverter(UserDtoConverter converter) {
        this.converter = converter;
    }

    public UserReviewDetailDto convert(UserReview userReview, RestaurantDto restaurantDto) {
        return new UserReviewDetailDto(
                userReview.getCreatedAt(),
                userReview.getRestaurantId(),
                restaurantDto.name(),
                userReview.getComment(),
                userReview.getUserRate(),
                converter.convert(userReview.getUser())
        );
    }
    public List<UserReviewDetailDto> convert(List<UserReview> userReviews, List<RestaurantDto> restaurantDtos) {
        return userReviews.stream()
                .map(review -> {
                    return restaurantDtos.stream()
                            .filter(restaurant -> restaurant.id().equals(review.getRestaurantId()))
                            .findFirst()
                            .map(matchedRestaurant -> new UserReviewDetailDto(
                                    review.getCreatedAt(),
                                    matchedRestaurant.id(),
                                    matchedRestaurant.name(),
                                    review.getComment(),
                                    review.getUserRate(),
                                    converter.convert(review.getUser())
                            ))
                            .orElse(null);
                })
                .collect(Collectors.toList());
    }


    public List<UserReviewDetailDto> convert(List<UserReview> userReviews, RestaurantDto restaurantDto) {
        return userReviews.stream()
                .map(review -> {
                    return new UserReviewDetailDto(
                            review.getCreatedAt(),
                            Objects.requireNonNull(restaurantDto).id(),
                            Objects.requireNonNull(restaurantDto).name(),
                            review.getComment(),
                            review.getUserRate(),
                            converter.convert(review.getUser())
                    );
                })
                .collect(Collectors.toList());
    }
}
