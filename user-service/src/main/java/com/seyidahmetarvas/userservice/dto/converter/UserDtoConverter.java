package com.seyidahmetarvas.userservice.dto.converter;

import com.seyidahmetarvas.userservice.dto.response.UserDto;
import com.seyidahmetarvas.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {
    public UserDto convert(User user) {
        return new UserDto(
                user.getName(),
                user.getSurname(),
                user.getBirthDate(),
                user.getEmail(),
                user.getGender(),
                user.getUserStatus(),
                user.getLatitude(),
                user.getLongitude()
        );
    }
}
