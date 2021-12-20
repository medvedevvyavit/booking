package ru.development.booking.service;

import ru.development.booking.dto.SignInDto;

import java.util.Map;

public interface AuthenticationService {

    Map<String, String> getRefreshToken(String refreshToken);
    Map<String, String> signIn(SignInDto request);
}
