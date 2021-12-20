package ru.development.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.development.booking.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
