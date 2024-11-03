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
                userReview.getId(),
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
                    RestaurantDto matchedRestaurant = restaurantDtos.stream()
                            .filter(restaurant -> restaurant.id().equals(review.getRestaurantId()))
                            .findFirst()
                            .orElse(null);

                    return new UserReviewDetailDto(
                            review.getId(),
                            Objects.requireNonNull(matchedRestaurant).id(),
                            Objects.requireNonNull(matchedRestaurant).name(),
                            review.getComment(),
                            review.getUserRate(),
                            converter.convert(review.getUser())
                    );
                })
                .collect(Collectors.toList());
    }
}
