package ru.development.booking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

    @JsonIgnore
    private String refreshToken;

    @JsonIgnore
    private List<String> roles;
}
