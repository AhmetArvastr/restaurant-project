package com.seyidahmetarvas.userservice.service;

import com.seyidahmetarvas.userservice.client.RestaurantClient;
import com.seyidahmetarvas.userservice.dto.converter.UserReviewDetailDtoConverter;
import com.seyidahmetarvas.userservice.dto.converter.UserReviewDtoConverter;
import com.seyidahmetarvas.userservice.dto.converter.UserReviewSaveRequestConverterToUserReview;
import com.seyidahmetarvas.userservice.dto.request.UserReviewSaveRequest;
import com.seyidahmetarvas.userservice.dto.request.UserReviewUpdateRequest;
import com.seyidahmetarvas.userservice.dto.response.RestaurantDto;
import com.seyidahmetarvas.userservice.dto.response.UserReviewDetailDto;
import com.seyidahmetarvas.userservice.dto.response.UserReviewDto;
import com.seyidahmetarvas.userservice.exception.UserNotActiveException;
import com.seyidahmetarvas.userservice.exception.UserReviewNotFoundException;
import com.seyidahmetarvas.userservice.model.UserReview;
import com.seyidahmetarvas.userservice.model.enums.Status;
import com.seyidahmetarvas.userservice.repository.UserReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserReviewService {

    private final UserReviewRepository repository;
    private final RestaurantClient restaurantClient;
    private final UserReviewDtoConverter converter;
    private final UserReviewDetailDtoConverter detailConverter;
    private final UserReviewSaveRequestConverterToUserReview toUserReview;
    private final UserService userService;

    public UserReviewService (UserReviewRepository repository,
                              RestaurantClient restaurantClient,
                              UserReviewDtoConverter converter,
                              UserReviewDetailDtoConverter detailConverter,
                              UserReviewSaveRequestConverterToUserReview toUserReview,
                              UserService userService) {
        this.repository = repository;
        this.restaurantClient = restaurantClient;
        this.converter = converter;
        this.detailConverter = detailConverter;
        this.toUserReview = toUserReview;
        this.userService = userService;
    }

    protected UserReview findUserReviewById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserReviewNotFoundException("UserReview could not find by id: " + id));
    }

    public UserReviewDto getUserReviewById(Long id) {
        return converter.convert(findUserReviewById(id));
    }

    public UserReviewDetailDto createUserReview(UserReviewSaveRequest request) {
        if(userService.getUserById(request.userId()).userStatus().equals(Status.INACTIVE)){
            throw new UserNotActiveException("User is not active");
        }
        RestaurantDto restaurantDTO = restaurantClient.getRestaurantById(request.restaurantId()).getBody().getData();
        UserReview userReview = new UserReview(
                restaurantDTO.id(),
                request.comment(),
                request.userRate(),
                userService.findUserById(request.userId())
        );
        return detailConverter.convert(repository.save(userReview), restaurantDTO);
    }

    public UserReviewDetailDto getUserReviewDetailById(Long id) {
        UserReview review = findUserReviewById(id);
        RestaurantDto restaurantDto = Objects.requireNonNull(
                restaurantClient.getRestaurantById(review.getRestaurantId()).getBody().getData());
        return detailConverter.convert(review, restaurantDto);
    }

    public List<UserReviewDetailDto> getAllUserReviews() {
        List<UserReview> userReviews = repository.findAll();
        List<RestaurantDto> restaurantDtos = Objects.requireNonNull(
                restaurantClient.getAllRestaurants().getBody()).getData();
        return detailConverter.convert(userReviews,restaurantDtos);
    }

    public List<UserReviewDetailDto> getUserReviewsByUserId(Long userId) {

        List<UserReview> userReviews = repository.findByUserId(userId);
        List <RestaurantDto> restaurantDtos = Objects.requireNonNull(
                restaurantClient.getAllRestaurants().getBody().getData());
        return detailConverter.convert(userReviews,restaurantDtos);

    }

    public List<UserReviewDetailDto> getUserReviewsByRestaurantId(String restaurantId) {
        List<UserReview> userReviews = repository.findByRestaurantId(restaurantId);
        RestaurantDto restaurantDTO = Objects.requireNonNull(
                restaurantClient.getRestaurantById(restaurantId).getBody().getData());
        return detailConverter.convert(userReviews, restaurantDTO);
    }

    public UserReviewDetailDto editUserReview(UserReviewUpdateRequest request) {
        UserReview review = findUserReviewById(request.id());
        RestaurantDto restaurantDto = Objects.requireNonNull(
                restaurantClient.getRestaurantById(review.getRestaurantId()).getBody().getData());
        return detailConverter.convert(
                repository.save(toUserReview.convert(review, request)),restaurantDto);
    }

    public void deleteUserReview(Long id) {
        UserReview review = findUserReviewById(id);
        repository.delete(review);
    }
}
