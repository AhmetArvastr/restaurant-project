package com.seyidahmetarvas.userservice.repository;

import com.seyidahmetarvas.userservice.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
    List<UserReview> findByUserId(Long userId);
    List<UserReview> findByRestaurantId(String restaurantId);
}
