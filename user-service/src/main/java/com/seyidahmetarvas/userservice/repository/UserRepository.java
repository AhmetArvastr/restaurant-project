package com.seyidahmetarvas.userservice.repository;

import com.seyidahmetarvas.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
