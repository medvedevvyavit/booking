package ru.development.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.development.booking.dto.UserDto;
import ru.development.booking.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }
}
