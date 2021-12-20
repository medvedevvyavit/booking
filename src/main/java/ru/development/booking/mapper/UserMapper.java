package ru.development.booking.mapper;

import org.mapstruct.Mapper;
import ru.development.booking.dto.AdminUserDto;
import ru.development.booking.dto.UserDto;
import ru.development.booking.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);
    User toUser(UserDto userDto);
    AdminUserDto toAdminUserDto(User user);
}
