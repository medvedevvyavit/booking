package ru.development.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.development.booking.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);
}
