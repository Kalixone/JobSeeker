package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.*;
import com.cvenjoyer.cv_enjoyer.exceptions.AuthenticationException;
import com.cvenjoyer.cv_enjoyer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/update/frameworks")
    UserDto updateFrameworks(Authentication authentication, @RequestBody UserUpdateFrameworksRequestDto userUpdateFrameworksRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.updateFrameworks(authentication, userUpdateFrameworksRequestDto);
    }

    @PutMapping("/update/english-level")
    UserDto updateEnglishLevel(Authentication authentication, @RequestBody UserUpdateEnglishLevelRequestDto userUpdateEnglishLevelRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.updateEnglishLevel(authentication, userUpdateEnglishLevelRequestDto);
    }

    @PutMapping("/update/programming-language")
    UserDto updateProgrammingLanguage(Authentication authentication, @RequestBody UserUpdateProgrammingLanguageRequestDto userUpdateProgrammingLanguageRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.updateProgrammingLanguages(authentication, userUpdateProgrammingLanguageRequestDto);
    }

    @PutMapping("/update/experience-level")
    UserDto updateExperienceLevel(Authentication authentication, @RequestBody UserUpdateExperienceLevelRequestDto userUpdateExperienceLevelRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.updateExperienceLevel(authentication, userUpdateExperienceLevelRequestDto);
    }

    @GetMapping("/profile/info")
    UserDto myProfileInfo(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.myProfileInfo(authentication);
    }
}
