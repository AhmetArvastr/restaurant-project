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
import com.seyidahmetarvas.userservice.exception.UserNotFoundException;
import com.seyidahmetarvas.userservice.model.UserReview;
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

    public UserReviewService (UserReviewRepository repository,
                              RestaurantClient restaurantClient, UserReviewDtoConverter converter,
                              UserReviewDetailDtoConverter detailConverter,
                              UserReviewSaveRequestConverterToUserReview toUserReview) {
        this.repository = repository;
        this.restaurantClient = restaurantClient;
        this.converter = converter;
        this.detailConverter = detailConverter;
        this.toUserReview = toUserReview;
    }

    protected UserReview findUserReviewById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("UserReview could not find by id: " + id));
    }

    public UserReviewDto getUserReviewById(Long id) {
        return converter.convert(findUserReviewById(id));
    }

    public UserReviewDetailDto createUserReview(UserReviewSaveRequest request) {
        RestaurantDto restaurantDTO = Objects.requireNonNull(
                restaurantClient.getRestaurantById(request.restaurantId()).getBody()).getData();
        UserReview userReview = new UserReview(
                request.restaurantId(),
                request.comment(),
                request.userRate(),
                request.user()
        );
        return detailConverter.convert(repository.save(userReview), restaurantDTO);
    }

    public UserReviewDetailDto getUserReviewDetailById(Long id) {
        RestaurantDto restaurantDto = Objects.requireNonNull(
                restaurantClient.getRestaurantById(findUserReviewById(id).getRestaurantId()).getBody()).getData();
        return detailConverter.convert(findUserReviewById(id), restaurantDto);
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
                restaurantClient.getAllRestaurants().getBody()).getData();
        return detailConverter.convert(userReviews,restaurantDtos);

    }

    public List<UserReviewDetailDto> getUserReviewsByRestaurantId(String restaurantId) {
        List<UserReview> userReviews = repository.findByRestaurantId(restaurantId);
        RestaurantDto restaurantDTO = Objects.requireNonNull(
                restaurantClient.getRestaurantById(restaurantId).getBody()).getData();
        return detailConverter.convert(userReviews, List.of(restaurantDTO));
    }

    public UserReviewDetailDto editUserReview(UserReviewUpdateRequest request) {
        RestaurantDto restaurantDto = Objects.requireNonNull(
                restaurantClient.getRestaurantById(
                        findUserReviewById(request.id()).getRestaurantId()).getBody()).getData();
        return detailConverter.convert(
                repository.save(toUserReview.convert(findUserReviewById(request.id()), request)),
                restaurantDto);
    }

    public void deleteUserReview(Long id) {
        repository.deleteById(findUserReviewById(id).getId());
    }
}
