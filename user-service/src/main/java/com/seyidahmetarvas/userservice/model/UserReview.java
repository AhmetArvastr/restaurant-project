package com.seyidahmetarvas.userservice.model;

import com.seyidahmetarvas.userservice.common.base.BaseEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "user_reviews")
public class UserReview extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "restaurant_id", length = 50, nullable = false)
    private String restaurantId;

    @Column(name = "comment", length = 500, nullable = false)
    private String comment;

    @Column(name = "user_rate", nullable = false, precision = 3, scale = 2)
    private BigDecimal userRate;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public UserReview() {
    }

    public UserReview(String restaurantId, String comment, BigDecimal userRate, User user) {
        this.restaurantId = restaurantId;
        this.comment = comment;
        this.userRate = userRate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getUserRate() {
        return userRate;
    }

    public void setUserRate(BigDecimal userRate) {
        this.userRate = userRate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReview that = (UserReview) o;
        return Objects.equals(id, that.id) && Objects.equals(restaurantId, that.restaurantId) && Objects.equals(comment, that.comment) && Objects.equals(userRate, that.userRate) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId, comment, userRate, user);
    }
}
