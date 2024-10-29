package com.seyidahmetarvas.userservice.dto.converter;

import com.seyidahmetarvas.userservice.dto.request.UserReviewUpdateRequest;
import com.seyidahmetarvas.userservice.model.UserReview;

public class UserReviewSaveRequestConverterToUserReview {
    public UserReview convert(UserReview userReview, UserReviewUpdateRequest userReviewUpdateRequest) {
        userReview.setRestaurantId(userReviewUpdateRequest.restaurantId());
        userReview.setComment(userReviewUpdateRequest.comment());
        userReview.setUserRate(userReviewUpdateRequest.userRate());
        return userReview;
    }
}
