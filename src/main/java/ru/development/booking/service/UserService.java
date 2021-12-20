package ru.development.booking.service;

import ru.development.booking.dto.SignUpDto;
import ru.development.booking.dto.UserDto;
import ru.development.booking.model.User;

import java.util.List;

public interface UserService {

    User register(SignUpDto signUpDto);
    List<User> getAll();
    UserDto findByUsername(String username);
    UserDto findById(Long id);
    void delete(Long id);
    UserDto save(UserDto user);
}
