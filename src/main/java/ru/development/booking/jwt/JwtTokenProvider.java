package ru.development.booking.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.development.booking.config.JwtTokenProperties;
import ru.development.booking.exception.JwtAuthenticationException;
import ru.development.booking.model.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private final JwtTokenProperties properties;

    public String createAccessToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validityAccess = new Date(now.getTime() + properties.getExpiredAccess());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validityAccess)
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }

    public String createRefreshToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);

        Date now = new Date();
        Date validityRefresh = new Date(now.getTime() + properties.getExpiredRefresh());

        return Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now)
                .setExpiration(validityRefresh)
                .signWith(SignatureAlgorithm.HS256, properties.getSecret())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(properties.getSecret())
                    .parseClaimsJws(token)
                    .getBody();

            return !claims.getExpiration().before(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }
}
