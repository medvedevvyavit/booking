package ru.development.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.development.booking.dto.SignInDto;
import ru.development.booking.dto.SignUpDto;
import ru.development.booking.model.User;
import ru.development.booking.service.AuthenticationService;
import ru.development.booking.service.UserService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService service;

    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody SignInDto request) {
        return ResponseEntity.ok(service.signIn(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(userService.register(signUpDto));
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestParam String refreshToken) {
        return ResponseEntity.ok(service.getRefreshToken(refreshToken));
    }
}
