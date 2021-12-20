package ru.development.booking.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.development.booking.jwt.JwtConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String SIGNIN_ENDPOINT = "/api/v1/auth/signin";
    private static final String SIGNUP_ENDPOINT = "/api/v1/auth/signup";
    private static final String REFRESH_TOKEN_ENDPOINT = "/api/v1/auth/token";

    private final JwtConfigurer configurer;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(SIGNIN_ENDPOINT).permitAll()
                .antMatchers(SIGNUP_ENDPOINT).permitAll()
                .antMatchers(REFRESH_TOKEN_ENDPOINT).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .apply(configurer);
    }
}
