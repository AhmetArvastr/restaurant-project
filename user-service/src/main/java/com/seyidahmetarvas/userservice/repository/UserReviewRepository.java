package com.seyidahmetarvas.userservice.repository;

import com.seyidahmetarvas.userservice.model.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
