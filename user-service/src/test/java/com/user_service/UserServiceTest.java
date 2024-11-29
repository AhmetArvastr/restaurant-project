package com.user_service;

import com.seyidahmetarvas.userservice.dto.converter.UserDtoConverter;
import com.seyidahmetarvas.userservice.dto.converter.UserSaveRequestConverterToUser;
import com.seyidahmetarvas.userservice.dto.request.UserSaveRequest;
import com.seyidahmetarvas.userservice.dto.request.UserUpdateRequest;
import com.seyidahmetarvas.userservice.dto.response.UserDto;
import com.seyidahmetarvas.userservice.exception.UserNotFoundException;
import com.seyidahmetarvas.userservice.model.User;
import com.seyidahmetarvas.userservice.model.enums.Gender;
import com.seyidahmetarvas.userservice.model.enums.Status;
import com.seyidahmetarvas.userservice.repository.UserRepository;
import com.seyidahmetarvas.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {

    private UserService userService;

    private UserRepository userRepository;
    private UserDtoConverter converter;
    private UserSaveRequestConverterToUser saveRequestConverter;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        converter = Mockito.mock(UserDtoConverter.class);
        saveRequestConverter = Mockito.mock(UserSaveRequestConverterToUser.class);

        userService = new UserService(userRepository, converter, saveRequestConverter);
    }

    @DisplayName("createUser should return UserDto when the UserSaveRequest parameters are present.")
    @Test
    void shouldreturnUserDtowhenUserSaveRequestparametersarepresent(){
        Long userId = 1L;
        UserSaveRequest request = new UserSaveRequest(
                "test", "test",
                LocalDate.of(1990, 1, 1),"test@gmail.com", Gender.MALE, Status.INACTIVE, 1.0, -1.0);
        User user = new User(
                userId,"test", "test",
                LocalDate.of(1990, 1, 1), "test@gmail.com", Gender.MALE, Status.ACTIVE, 1.0, -1.0);
        UserDto userDto = new UserDto(
                "test", "test",
                LocalDate.of(1990, 1, 1),"test@gmail.com",Gender.MALE,Status.ACTIVE,1.0,-1.0);

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(converter.convert(user)).thenReturn(userDto);

        UserDto result = userService.createUser(request);

        assertEquals(userDto, result);

        Mockito.verify(userRepository).save(Mockito.any(User.class));
        Mockito.verify(converter).convert(user);
    }

    @DisplayName("getUserById should return UserDto when the userId parameter are present.")
    @Test
    void shouldreturnUserDtowhenuserIdparameterarepresent(){
        Long userId = 1L;
        User user = new User(
                userId,"test", "test",
                LocalDate.of(1990, 1, 1), "test@gmail.com", Gender.MALE, Status.ACTIVE, 1.0, -1.0);
        UserDto userDto = new UserDto(
                "test", "test",
                LocalDate.of(1990, 1, 1),"test@gmail.com",Gender.MALE,Status.ACTIVE,1.0,-1.0);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(converter.convert(user)).thenReturn(userDto);

        UserDto result = userService.getUserById(userId);

        assertEquals(userDto, result);

        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(converter).convert(user);
    }

    @DisplayName("getAllUsers should return a UserDto list if User is a list.")
    @Test
    void shouldreturnlistofUserDtowhenUseralist(){
        Long userId = 1L;
        List<User> users = List.of(
                new User(userId,"John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com", Gender.MALE, Status.ACTIVE, 1.0, -1.0),
                new User(userId,"Jane", "Doe", LocalDate.of(1992, 2, 2), "jane.doe@example.com", Gender.FEMALE, Status.ACTIVE, 1.0, -1.0)
        );

        List<UserDto> userDtos = List.of(
                new UserDto("John", "Doe", LocalDate.of(1990, 1, 1), "john.doe@example.com", Gender.MALE, Status.ACTIVE,1.0, -1.0),
                new UserDto("Jane", "Doe", LocalDate.of(1992, 2, 2), "jane.doe@example.com", Gender.FEMALE, Status.ACTIVE,1.0, -1.0)
        );

        Mockito.when(userRepository.findAll()).thenReturn(users);
        Mockito.when(converter.convert(users.get(0))).thenReturn(userDtos.get(0));
        Mockito.when(converter.convert(users.get(1))).thenReturn(userDtos.get(1));

        List<UserDto> result = userService.getAllUsers();

        assertEquals(userDtos, result);

        Mockito.verify(userRepository).findAll();
        Mockito.verify(converter).convert(users.get(0));
        Mockito.verify(converter).convert(users.get(1));
        Mockito.verify(converter, Mockito.times(2)).convert(Mockito.any(User.class));
    }

    @DisplayName("updateUser should return UserDto when the UserUpdateRequest parameters are present.")
    @Test
    void shouldreturnUserDtowhenUserUpdateRequestparameterssarepresent(){
        Long userId = 1L;
        UserUpdateRequest request = new UserUpdateRequest(
                userId,"test2", "test2",
                LocalDate.of(1990, 1, 1), "test2@gmail.com", Gender.FEMALE, Status.INACTIVE, 3.0, -3.0);
        User user = new User(
                userId,"test", "test",
                LocalDate.of(1990, 1, 1), "test@gmail.com", Gender.MALE, Status.ACTIVE, 1.0, -1.0);
        User updater = new User(
                userId,"test2", "test2",
                LocalDate.of(1990, 1, 1), "test2@gmail.com", Gender.FEMALE, Status.INACTIVE, 3.0, -3.0);
        UserDto userDto = new UserDto(
                "test2", "test2",
                LocalDate.of(1990, 1, 1), "test2@gmail.com", Gender.FEMALE, Status.INACTIVE, 3.0, -3.0);

        Mockito.when(userRepository.findById(request.id())).thenReturn(Optional.of(user));
        Mockito.when(saveRequestConverter.convert(user, request)).thenReturn(updater);
        Mockito.when(userRepository.save(updater)).thenReturn(updater);
        Mockito.when(converter.convert(updater)).thenReturn(userDto);

        UserDto result = userService.updateUser(request);

        assertEquals(userDto, result);

        Mockito.verify(userRepository).findById(Mockito.any(Long.class));
        Mockito.verify(saveRequestConverter).convert(user,request);
        Mockito.verify(userRepository).save(updater);
        Mockito.verify(converter).convert(updater);
    }

    @DisplayName("deleteUserById should delete User when UserId parameter present.")
    @Test
    void shoulddeleteUserwhenuserIdparameterpresent(){
        Long userId = 1L;

        User user = new User(
                userId,"test", "test",
                LocalDate.of(1990, 1, 1), "test@gmail.com", Gender.MALE, Status.ACTIVE, 1.0, -1.0);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.doNothing().when(userRepository).delete(user);

        userService.deleteUserById(userId);

        Mockito.verify(userRepository).findById(userId);
        Mockito.verify(userRepository).delete(user);
    }

    @DisplayName("should Throw UserNotFoundException when the parameter of the getUserById userId Does Not Exist")
    @Test
    void shouldThrowUserNotFoundException_whenuserIdDoesNotExist() {

        Long userId = 1L;

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class,
                () -> userService.getUserById(userId));

        Mockito.verify(userRepository).findById(userId);
        Mockito.verifyNoInteractions(converter);
    }
}
