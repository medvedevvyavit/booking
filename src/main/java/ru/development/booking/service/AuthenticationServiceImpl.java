package ru.development.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.development.booking.dto.SignInDto;
import ru.development.booking.dto.UserDto;
import ru.development.booking.exception.AppException;
import ru.development.booking.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public Map<String, String> getRefreshToken(String refreshToken) {
        String username = jwtTokenProvider.getUsername(refreshToken);
        UserDto user = userService.findByUsername(username);

        if (!user.getRefreshToken().equals(refreshToken)) {
            throw new AppException("Current refresh token not found");
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(username, user.getRoles());
        String newRefreshToken = jwtTokenProvider.createRefreshToken(username);

        Map<String, String> response = new HashMap<>();
        response.put("newAccessToken", newAccessToken);
        response.put("newRefreshToken", newRefreshToken);

        user.setRefreshToken(refreshToken);
        userService.save(user);

        return response;
    }

    @Override
    public Map<String, String> signIn(SignInDto request) {
        try {
            String username = request.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, request.getPassword()));
            UserDto user = userService.findByUsername(username);

            String accessToken = jwtTokenProvider.createAccessToken(username, user.getRoles());
            String refreshToken = jwtTokenProvider.createRefreshToken(username);

            Map<String, String> response = new HashMap<>();
            response.put("username", username);
            response.put("accessToken", accessToken);
            response.put("refreshToken", refreshToken);

            user.setRefreshToken(refreshToken);
            userService.save(user);

            return response;

        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
