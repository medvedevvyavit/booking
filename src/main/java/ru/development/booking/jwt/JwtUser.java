package ru.development.booking.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

@Data
public class JwtUser implements UserDetails {

    @JsonIgnore
    private final Long id;
    private final String username;
    private final String firstName;
    private final String lastName;

    @JsonIgnore
    private final String password;
    private final String email;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private final LocalDate lastPasswordResetDate;

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
