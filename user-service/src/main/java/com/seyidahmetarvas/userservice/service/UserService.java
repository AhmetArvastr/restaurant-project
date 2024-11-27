package com.seyidahmetarvas.userservice.service;

import com.seyidahmetarvas.userservice.common.audit.AuditorImpl;
import com.seyidahmetarvas.userservice.dto.converter.UserDtoConverter;
import com.seyidahmetarvas.userservice.dto.converter.UserSaveRequestConverterToUser;
import com.seyidahmetarvas.userservice.dto.request.UserSaveRequest;
import com.seyidahmetarvas.userservice.dto.request.UserUpdateRequest;
import com.seyidahmetarvas.userservice.dto.response.UserDto;
import com.seyidahmetarvas.userservice.exception.UserNotFoundException;
import com.seyidahmetarvas.userservice.model.User;
import com.seyidahmetarvas.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDtoConverter converter;
    private final UserSaveRequestConverterToUser converterToUser;

    public UserService(UserRepository userRepository, UserDtoConverter converter, UserSaveRequestConverterToUser converterToUser) {
        this.userRepository = userRepository;
        this.converter = converter;
        this.converterToUser = converterToUser;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User could not find by id: " + id));
    }

    public UserDto getUserById(Long id) {
        return converter.convert(findUserById(id));
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public UserDto createUser(UserSaveRequest usersaveRequest) {
        User user = new User(
                usersaveRequest.name(),
                usersaveRequest.surname(),
                usersaveRequest.birthDate(),
                usersaveRequest.email(),
                usersaveRequest.gender(),
                usersaveRequest.userStatus(),
                usersaveRequest.latitude(),
                usersaveRequest.longitude());

        AuditorImpl.setCurrentUser(1L);

        return converter.convert(userRepository.save(user));
    }

    public UserDto updateUser(UserUpdateRequest userupdateRequest) {
        AuditorImpl.setCurrentUser(userupdateRequest.id());
        return converter.convert(
                userRepository.save(converterToUser.convert(
                        findUserById(userupdateRequest.id()),userupdateRequest)
                )
        );
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
