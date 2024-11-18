package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.UserRegistrationRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UserRegistrationResponseDto;
import com.cvenjoyer.cv_enjoyer.exceptions.RegistrationException;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
}
