package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.*;
import com.cvenjoyer.cv_enjoyer.exceptions.RegistrationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) throws RegistrationException;
    UserDto updateFrameworks(Authentication authentication, UserUpdateFrameworksRequestDto userUpdateFrameworksRequestDto);
    UserDto updateProgrammingLanguages(Authentication authentication, UserUpdateProgrammingLanguageRequestDto userUpdateProgrammingLanguageRequestDto);
    UserDto updateExperienceLevel(Authentication authentication, UserUpdateExperienceLevelRequestDto userUpdateExperienceLevelRequestDto);
    UserDto updateEnglishLevel(Authentication authentication, UserUpdateEnglishLevelRequestDto userUpdateEnglishLevelRequestDto);
    UserDto myProfileInfo(Authentication authentication);
    UserDto updateCity(Authentication authentication, UpdateCityRequestDto updateCityRequestDto);
    UserDto resetFrameworks(Authentication authentication);
    UserDto resetProgrammingLanguages(Authentication authentication);
    UserDto resetExperienceLevel(Authentication authentication);
}
