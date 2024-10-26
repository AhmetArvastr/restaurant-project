package com.seyidahmetarvas.userservice.dto.request;

import com.seyidahmetarvas.userservice.model.enums.Gender;
import com.seyidahmetarvas.userservice.model.enums.Status;

import java.time.LocalDate;

public record UserUpdateRequest(
        Long id,
        String name,
        String surname,
        LocalDate birthDate,
        String email,
        Gender gender,
        Status userStatus,
        double latitude,
        double longitude
) {
}
