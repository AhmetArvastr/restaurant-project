package com.userreview_service;

import com.seyidahmetarvas.userservice.client.RestaurantClient;
import com.seyidahmetarvas.userservice.common.base.RestResponse;
import com.seyidahmetarvas.userservice.dto.converter.*;
import com.seyidahmetarvas.userservice.dto.request.UserReviewSaveRequest;
import com.seyidahmetarvas.userservice.dto.request.UserReviewUpdateRequest;
import com.seyidahmetarvas.userservice.dto.response.RestaurantDto;
import com.seyidahmetarvas.userservice.dto.response.UserDto;
import com.seyidahmetarvas.userservice.dto.response.UserReviewDetailDto;
import com.seyidahmetarvas.userservice.dto.response.UserReviewDto;
import com.seyidahmetarvas.userservice.exception.UserNotActiveException;
import com.seyidahmetarvas.userservice.exception.UserReviewNotFoundException;
import com.seyidahmetarvas.userservice.model.User;
import com.seyidahmetarvas.userservice.model.UserReview;
import com.seyidahmetarvas.userservice.model.enums.Gender;
import com.seyidahmetarvas.userservice.model.enums.Status;
import com.seyidahmetarvas.userservice.repository.UserReviewRepository;
import com.seyidahmetarvas.userservice.service.UserReviewService;
import com.seyidahmetarvas.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserReviewServiceTest {

    private UserReviewService userReviewService;

    private UserReviewRepository repository;
    private RestaurantClient restaurantClient;
    private UserReviewDtoConverter converter;
    private UserReviewDetailDtoConverter detailConverter;
    private UserReviewSaveRequestConverterToUserReview toUserReview;
    private UserService userService;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(UserReviewRepository.class);
        restaurantClient = Mockito.mock(RestaurantClient.class);
        converter = Mockito.mock(UserReviewDtoConverter.class);
        detailConverter = Mockito.mock(UserReviewDetailDtoConverter.class);
        toUserReview = Mockito.mock(UserReviewSaveRequestConverterToUserReview.class);
        userService = Mockito.mock(UserService.class);

        userReviewService = new UserReviewService(repository, restaurantClient, converter, detailConverter, toUserReview, userService);
    }

    @DisplayName("should Return UserReviewDto with UserDto when the parameter of the getUserReviewById UserReviewId Exist")
    @Test
    void shouldReturnUserReviewWithUserDto_whenUserReviewIdExist(){
        Long userReviewId = 1L;
        Long userId = 1L;

        User user = new User(
                userId,"test", "test",
                LocalDate.of(1990, 1, 1), "test@gmail.com", Gender.MALE, Status.ACTIVE, 1.0, -1.0);
        UserDto userdto = new UserDto(
                userId, "test", "test",
                LocalDate.of(1990, 1, 1), "test@gmail.com", Gender.MALE, Status.ACTIVE, 1.0, -1.0);
        UserReview userReview = new UserReview("test","test", BigDecimal.valueOf(0),user);
        UserReviewDto userReviewDto = new UserReviewDto("test", "test", BigDecimal.valueOf(0), userdto);

        Mockito.when(repository.findById(userReviewId)).thenReturn(Optional.of(userReview));
        Mockito.when(converter.convert(userReview)).thenReturn(userReviewDto);

        UserReviewDto result = userReviewService.getUserReviewById(userReviewId);

        assertEquals(userReviewDto, result);

        Mockito.verify(repository).findById(userReviewId);
        Mockito.verify(converter).convert(userReview);
    }

    @DisplayName("should Return UserReviewDetailDto with UserDto with RestaurantId with RestaurantName And Create UserReview when the parameter of the createUserReview UserReviewSaveRequest Exist")
    @Test
    void shouldReturnUserReviewDetailDtoWithUserDto_whenUserReviewSaveRequestExist() {
        Long userId = 1L;
        String restaurantId = "test";

        UserReviewSaveRequest request = new UserReviewSaveRequest(
                restaurantId, "test", BigDecimal.valueOf(0), userId);
        User user = new User(
                userId, "test", "test", LocalDate.of(1990, 1, 1), "test",
                Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        UserDto userDto = new UserDto(userId, "test", "test", LocalDate.of(1990, 1, 1),
                "test", Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        RestaurantDto restaurantDto = new RestaurantDto(
                restaurantId, "test", "test", "test", "test", "test", "test",
                "test", BigDecimal.valueOf(0), Status.ACTIVE);
        UserReview userReview = new UserReview(restaurantId, "test", BigDecimal.valueOf(0), user);
        UserReviewDetailDto detailDto = new UserReviewDetailDto(LocalDateTime.now(),
                restaurantId, "test", "test", BigDecimal.valueOf(0), userDto);

        Mockito.when(userService.getUserById(userId)).thenReturn(userDto);
        Mockito.when(restaurantClient.getRestaurantById(restaurantId)).thenReturn(ResponseEntity.ok(RestResponse.of(restaurantDto, "tested")));
        Mockito.when(repository.save(Mockito.any(UserReview.class))).thenReturn(userReview);
        Mockito.when(detailConverter.convert(userReview, restaurantDto)).thenReturn(detailDto);

        UserReviewDetailDto result = userReviewService.createUserReview(request);

        assertEquals(detailDto, result);

        Mockito.verify(userService).getUserById(userId);
        Mockito.verify(restaurantClient).getRestaurantById(restaurantId);
        Mockito.verify(repository).save(Mockito.any(UserReview.class));
        Mockito.verify(detailConverter).convert(userReview, restaurantDto);
    }

    @DisplayName("should Return UserReviewDetailDto with UserDto with RestaurantId and RestaurantName when the parameter of the getUseReviewDetailById UserReviewId Exist")
    @Test
    void shouldReturnUserReviewDetailDtoWithUserDtoWithRestaurantId_whenUserReviewIdExist() {
        Long userId = 1L;
        Long userReviewId = 1L;
        String restaurantId = "test";

        User user = new User(
                userId, "test", "test", LocalDate.of(1990, 1, 1), "test",
                Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        UserDto userDto = new UserDto(userId, "test", "test", LocalDate.of(1990, 1, 1),
                "test", Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        RestaurantDto restaurantDto = new RestaurantDto(
                restaurantId, "test", "test", "test", "test", "test", "test",
                "test", BigDecimal.valueOf(0), Status.ACTIVE);
        UserReview userReview = new UserReview(restaurantId, "test", BigDecimal.valueOf(0), user);
        UserReviewDetailDto detailDto = new UserReviewDetailDto(LocalDateTime.now(),
                restaurantId, "test", "test", BigDecimal.valueOf(0), userDto);

        Mockito.when(repository.findById(userReviewId)).thenReturn(Optional.of(userReview));
        Mockito.when(restaurantClient.getRestaurantById(userReview.getRestaurantId())).thenReturn(ResponseEntity.ok(RestResponse.of(restaurantDto, "tested")));
        Mockito.when(detailConverter.convert(userReview, restaurantDto)).thenReturn(detailDto);

        UserReviewDetailDto result = userReviewService.getUserReviewDetailById(userReviewId);

        assertEquals(detailDto, result);

        Mockito.verify(repository).findById(userReviewId);
        Mockito.verify(restaurantClient).getRestaurantById(restaurantId);
        Mockito.verify(detailConverter).convert(userReview, restaurantDto);
    }

    @DisplayName("should Return the UserReviewDetailDto list with UserDto with RestaurantId and RestaurantName")
    @Test
    void shouldReturnUserReviewDetailDtoListWithUserDtoWithRestaurantIdAndRestaurantName(){
        Long userId = 1L;
        String restaurantId = "test";

        User user = new User(
                userId, "test", "test", LocalDate.of(1990, 1, 1), "test",
                Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        UserDto userDto = new UserDto(userId, "test", "test", LocalDate.of(1990, 1, 1),
                "test", Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        List<RestaurantDto> restaurantDtos = List.of(
                new RestaurantDto(
                        restaurantId, "test", "test", "test", "test", "test",
                        "test", "test", BigDecimal.valueOf(0), Status.ACTIVE));
        List<UserReview> userReviews = List.of(
                new UserReview(restaurantId, "test", BigDecimal.valueOf(0),user),
                new UserReview(restaurantId, "test2", BigDecimal.valueOf(1), user));
        List<UserReviewDetailDto> detailDtos =List.of(
                new UserReviewDetailDto(LocalDateTime.now(),restaurantId, "test", "test", BigDecimal.valueOf(0), userDto),
                new UserReviewDetailDto(LocalDateTime.now(),restaurantId, "test2", "test2", BigDecimal.valueOf(1), userDto));

        Mockito.when(repository.findAll()).thenReturn(userReviews);
        Mockito.when(restaurantClient.getAllRestaurants()).thenReturn(ResponseEntity.ok(RestResponse.of(restaurantDtos, "tested")));
        Mockito.when(detailConverter.convert(userReviews, restaurantDtos)).thenReturn(detailDtos);

        List<UserReviewDetailDto> result = userReviewService.getAllUserReviews();

        assertEquals(detailDtos, result);

        Mockito.verify(repository).findAll();
        Mockito.verify(restaurantClient).getAllRestaurants();
        Mockito.verify(detailConverter).convert(userReviews, restaurantDtos);
    }

    @DisplayName("should Return the UserReviewDetailDto list with UserDto with RestaurantId and RestaurantName when the parameter of the getUseReviewDetailByUserId UserId Exist")
    @Test
    void shouldReturnUserReviewDetailListWithUserDtowithRestaurantIdAndRestaurantName_whenUserIdExist(){
        Long userId = 1L;
        String restaurantId = "test";

        User user = new User(
                userId, "test", "test", LocalDate.of(1990, 1, 1), "test",
                Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        UserDto userDto = new UserDto(userId, "test", "test", LocalDate.of(1990, 1, 1),
                "test", Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        List<RestaurantDto> restaurantDtos = List.of(
                new RestaurantDto(
                        restaurantId, "test", "test", "test", "test", "test",
                        "test", "test", BigDecimal.valueOf(0), Status.ACTIVE));
        List<UserReview> userReviews = List.of(
                new UserReview(restaurantId, "test", BigDecimal.valueOf(0),user),
                new UserReview(restaurantId, "test2", BigDecimal.valueOf(1), user));
        List<UserReviewDetailDto> detailDtos =List.of(
                new UserReviewDetailDto(LocalDateTime.now(),restaurantId, "test", "test", BigDecimal.valueOf(0), userDto),
                new UserReviewDetailDto(LocalDateTime.now(),restaurantId, "test2", "test2", BigDecimal.valueOf(1), userDto));

        Mockito.when(repository.findByUserId(userId)).thenReturn(userReviews);
        Mockito.when(restaurantClient.getAllRestaurants()).thenReturn(ResponseEntity.ok(RestResponse.of(restaurantDtos,"tested")));
        Mockito.when(detailConverter.convert(userReviews, restaurantDtos)).thenReturn(detailDtos);

        List<UserReviewDetailDto> result = userReviewService.getUserReviewsByUserId(userId);

        assertEquals(detailDtos, result);

        Mockito.verify(repository).findByUserId(userId);
        Mockito.verify(restaurantClient).getAllRestaurants();
        Mockito.verify(detailConverter).convert(userReviews, restaurantDtos);
    }

    @DisplayName("should Return the UserReviewDetailDto with UserDto with RestaurantId and RestaurantName when the parameter of the editUserReview UserReviewUpdateRequest Exist")
    @Test
    void shouldReturnUserReviewDetailDtoWithUserDtoWithRestaurantIdAndRestaurantName_whenUserReviewsUpdateRequestExist(){
        Long userId = 1L;
        Long userReviewId = 1L;
        String restaurantId = "test";

        UserReviewUpdateRequest request = new UserReviewUpdateRequest(userReviewId,restaurantId,"test",BigDecimal.valueOf(0));
        User user = new User(
                userId, "test", "test", LocalDate.of(1990, 1, 1), "test",
                Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        UserDto userDto = new UserDto(userId, "test", "test", LocalDate.of(1990, 1, 1),
                "test", Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        RestaurantDto restaurantDto = new RestaurantDto(restaurantId, "test", "test", "test",
                "test", "test", "test", "test", BigDecimal.valueOf(0), Status.ACTIVE);
        UserReview updater =
                new UserReview(restaurantId, "test", BigDecimal.valueOf(0),user);
        UserReview userReview =
                new UserReview(restaurantId, "test", BigDecimal.valueOf(0),user);
        UserReviewDetailDto detailDto =
                new UserReviewDetailDto(LocalDateTime.now(),restaurantId, "test", "test", BigDecimal.valueOf(0), userDto);

        Mockito.when(repository.findById(userReviewId)).thenReturn(Optional.of(userReview));
        Mockito.when(restaurantClient.getRestaurantById(restaurantId)).thenReturn(ResponseEntity.ok(RestResponse.of(restaurantDto, "tested")));
        Mockito.when(toUserReview.convert(updater,request)).thenReturn(userReview);
        Mockito.when(repository.save(userReview)).thenReturn(userReview);
        Mockito.when(detailConverter.convert(userReview, restaurantDto)).thenReturn(detailDto);

        UserReviewDetailDto result = userReviewService.editUserReview(request);

        assertEquals(detailDto, result);

        Mockito.verify(repository).findById(userReviewId);
        Mockito.verify(restaurantClient).getRestaurantById(restaurantId);
        Mockito.verify(toUserReview).convert(updater,request);
        Mockito.verify(repository).save(userReview);
        Mockito.verify(detailConverter).convert(userReview, restaurantDto);
    }

    @DisplayName("should Delete UserReview when the parameter of the deleteUserReview UserReviewId Exist")
    @Test
    void shouldDeleteUserReview_whenUserReviewIdExist(){
        Long userReviewId = 1L;
        Long userId = 1L;
        String restaurantId = "test";

        User user = new User(
                userId, "test", "test", LocalDate.of(1990, 1, 1), "test",
                Gender.MALE, Status.ACTIVE, 40.0, 29.0);
        UserReview userReview =
                new UserReview(restaurantId, "test", BigDecimal.valueOf(0),user);

        Mockito.when(repository.findById(userReviewId)).thenReturn(Optional.of(userReview));
        Mockito.doNothing().when(repository).delete(userReview);

        userReviewService.deleteUserReview(userReviewId);

        Mockito.verify(repository).findById(userId);
        Mockito.verify(repository).delete(userReview);
    }

    @DisplayName("should Throw UserReviewNotFoundException when the parameter of the getUserReviewById with userId Does Not Exist")
    @Test
    void shouldThrowUserReviewNotFoundException_whenUserReviewIdDoesNotExist() {

        Long userReviewId= 1L;

        Mockito.when(repository.findById(userReviewId)).thenReturn(Optional.empty());

        assertThrows(UserReviewNotFoundException.class,
                () -> userReviewService.getUserReviewById(userReviewId));

        Mockito.verify(repository).findById(userReviewId);
        Mockito.verifyNoInteractions(restaurantClient);
        Mockito.verifyNoInteractions(converter);
        Mockito.verifyNoInteractions(detailConverter);
        Mockito.verifyNoInteractions(userService);
    }

    @DisplayName("should throw UserNotActiveException when getUserStatus is Inactive")
    @Test
    void shouldThrowUserNotActiveException_whenUserStatusIsInactive() {
        Long userId = 1L;
        String restaurantId = "test";

        UserReviewSaveRequest request = new UserReviewSaveRequest(
                restaurantId, "test", BigDecimal.valueOf(0), userId);

        UserDto userDto = new UserDto(userId, "test", "test", LocalDate.of(1990, 1, 1),
                "test", Gender.MALE, Status.INACTIVE, 40.0, 29.0);

        Mockito.when(userService.getUserById(userId)).thenReturn(userDto);

        assertThrows(UserNotActiveException.class,
                () -> userReviewService.createUserReview(request));

        Mockito.verify(userService).getUserById(userId);
        Mockito.verifyNoInteractions(restaurantClient);
        Mockito.verifyNoInteractions(repository);
        Mockito.verifyNoInteractions(detailConverter);
    }
}
