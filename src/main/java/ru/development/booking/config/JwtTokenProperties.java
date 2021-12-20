package ru.development.booking.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;

@Data
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenProperties {

    private String secret;
    private long expiredAccess;
    private long expiredRefresh;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }
}
