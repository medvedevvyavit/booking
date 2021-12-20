package ru.development.booking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.development.booking.jwt.JwtUser;
import ru.development.booking.mapper.JwtUserMapper;
import ru.development.booking.model.User;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        JwtUser jwtUser = JwtUserMapper.toJwtUser(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);

        return jwtUser;
    }
}
