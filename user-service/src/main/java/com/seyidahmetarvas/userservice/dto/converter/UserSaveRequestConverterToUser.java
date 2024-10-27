package com.seyidahmetarvas.userservice.dto.converter;

import com.seyidahmetarvas.userservice.dto.request.UserSaveRequest;
import com.seyidahmetarvas.userservice.dto.request.UserUpdateRequest;
import com.seyidahmetarvas.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserSaveRequestConverterToUser {
    public User convert(User user, UserUpdateRequest userupdateRequest) {
                user.setName(userupdateRequest.name());
                user.setSurname(userupdateRequest.surname());
                user.setBirthDate(userupdateRequest.birthDate());
                user.setEmail(userupdateRequest.email());
                user.setGender(userupdateRequest.gender());
                user.setUserStatus(userupdateRequest.userStatus());
                user.setLatitude(userupdateRequest.latitude());
                user.setLongitude(userupdateRequest.longitude());
                return user;
    }
}
