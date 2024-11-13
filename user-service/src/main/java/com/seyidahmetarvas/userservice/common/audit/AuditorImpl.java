package com.seyidahmetarvas.userservice.common.audit;

import com.seyidahmetarvas.userservice.dto.request.UserUpdateRequest;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorAware")
public class AuditorImpl implements AuditorAware<Long> {

    private static final ThreadLocal<Long> currentUser = new ThreadLocal<>();

    public static void setCurrentUser(Long userId) {
        currentUser.set(userId);
    }

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.ofNullable(currentUser.get());
    }

    public static void clear() {
        currentUser.remove();
    }
}
