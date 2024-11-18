package com.cvenjoyer.cv_enjoyer.mapper;

import com.cvenjoyer.cv_enjoyer.dto.UserDto;
import com.cvenjoyer.cv_enjoyer.dto.UserRegistrationResponseDto;
import com.cvenjoyer.cv_enjoyer.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRegistrationResponseDto toUserResponse(User requestDto);
    UserDto toDto(User user);
}
