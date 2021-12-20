package ru.development.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.development.booking.dto.SignUpDto;
import ru.development.booking.dto.UserDto;
import ru.development.booking.exception.AppException;
import ru.development.booking.exception.EntityAlreadyExistsException;
import ru.development.booking.mapper.UserMapper;
import ru.development.booking.model.Role;
import ru.development.booking.model.User;
import ru.development.booking.model.UserStatus;
import ru.development.booking.repository.RoleRepository;
import ru.development.booking.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static ru.development.booking.exception.ExceptionConstants.ENTITY_ALREADY_EXISTS_MSG;
import static ru.development.booking.exception.ExceptionConstants.ENTITY_NOT_FOUND_MSG;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USER = "User";

    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper mapper;

    @Override
    public User register(SignUpDto signUpDto) {
        if (repository.existsByUsername(signUpDto.getUsername()))
            throw new EntityAlreadyExistsException(format(ENTITY_ALREADY_EXISTS_MSG, USER, signUpDto.getUsername()));

        if (repository.existsByEmail(signUpDto.getEmail()))
            throw new EntityAlreadyExistsException(format(ENTITY_ALREADY_EXISTS_MSG, USER, signUpDto.getEmail()));

        Role roleUser = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new AppException("User Role not set"));
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        User user = new User();
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(UserStatus.ACTIVE);

        User registeredUser = repository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = repository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public UserDto findByUsername(String username) {
        UserDto result = repository.findByUsername(username)
                .map(mapper::toUserDto)
                .orElseThrow(() -> new EntityNotFoundException(format(ENTITY_NOT_FOUND_MSG, USER, username)));

        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public UserDto findById(Long id) {
        UserDto result = repository.findById(id)
                .map(mapper::toUserDto)
                .orElseThrow(() -> new EntityNotFoundException(format(ENTITY_NOT_FOUND_MSG, USER, id)));

        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }

    @Override
    public UserDto save(UserDto user) {
        User result = repository.save(mapper.toUser(user));

        return mapper.toUserDto(result);
    }
}
