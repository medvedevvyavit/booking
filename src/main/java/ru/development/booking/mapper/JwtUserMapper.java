package ru.development.booking.mapper;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.development.booking.jwt.JwtUser;
import ru.development.booking.model.Role;
import ru.development.booking.model.User;
import ru.development.booking.model.UserStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUserMapper {

    public static JwtUser toJwtUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword(),
                user.getEmail(),
                user.getStatus().equals(UserStatus.ACTIVE),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getUpdated().toLocalDate()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
