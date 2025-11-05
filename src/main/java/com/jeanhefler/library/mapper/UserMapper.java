package com.jeanhefler.library.mapper;

import com.jeanhefler.library.dto.UserDto;
import com.jeanhefler.library.model.User;

public class UserMapper {
    public static UserDto toDto(User user){
        return new UserDto(
        user.getName(),
        user.getEmail(),
        user.getRole()
        );
    }
}
