package ru.raspad.marketspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import ru.raspad.marketspring.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
