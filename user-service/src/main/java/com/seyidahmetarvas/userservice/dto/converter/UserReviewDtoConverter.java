package com.seyidahmetarvas.userservice.dto.converter;

import com.seyidahmetarvas.userservice.dto.response.UserReviewDto;
import com.seyidahmetarvas.userservice.model.UserReview;
import org.springframework.stereotype.Component;

@Component
public class UserReviewDtoConverter {
    private final UserDtoConverter converter;

    public UserReviewDtoConverter(UserDtoConverter converter) {
        this.converter = converter;
    }

    public UserReviewDto convert(UserReview userReview) {
        return new UserReviewDto(
                userReview.getRestaurantId(),
                userReview.getComment(),
                userReview.getUserRate(),
                converter.convert(userReview.getUser())
        );
    }
}
