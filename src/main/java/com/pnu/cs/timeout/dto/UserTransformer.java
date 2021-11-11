package com.pnu.cs.timeout.dto;

import com.pnu.cs.timeout.model.User;

public class UserTransformer {
    public static User toEntity(UserDto dto) {
        return User.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
