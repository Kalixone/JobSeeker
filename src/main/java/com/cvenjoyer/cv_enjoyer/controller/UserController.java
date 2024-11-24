package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.*;
import com.cvenjoyer.cv_enjoyer.exceptions.AuthenticationException;
import com.cvenjoyer.cv_enjoyer.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/update/frameworks")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update user frameworks", description = "Update the frameworks of the authenticated user.")
    UserDto updateFrameworks(Authentication authentication, @RequestBody UserUpdateFrameworksRequestDto userUpdateFrameworksRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.updateFrameworks(authentication, userUpdateFrameworksRequestDto);
    }

    @PutMapping("/update/english-level")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update user English level", description = "Update the English language proficiency level of the authenticated user.")
    UserDto updateEnglishLevel(Authentication authentication, @RequestBody UserUpdateEnglishLevelRequestDto userUpdateEnglishLevelRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.updateEnglishLevel(authentication, userUpdateEnglishLevelRequestDto);
    }

    @PutMapping("/update/programming-language")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update user programming languages", description = "Update the programming languages of the authenticated user.")
    UserDto updateProgrammingLanguage(Authentication authentication, @RequestBody UserUpdateProgrammingLanguageRequestDto userUpdateProgrammingLanguageRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.updateProgrammingLanguages(authentication, userUpdateProgrammingLanguageRequestDto);
    }

    @PutMapping("/update/experience-level")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update user experience level", description = "Update the experience level of the authenticated user.")
    UserDto updateExperienceLevel(Authentication authentication, @RequestBody UserUpdateExperienceLevelRequestDto userUpdateExperienceLevelRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.updateExperienceLevel(authentication, userUpdateExperienceLevelRequestDto);
    }

    @GetMapping("/profile/info")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get user profile info", description = "Fetch the profile information of the authenticated user.")
    UserDto myProfileInfo(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.myProfileInfo(authentication);
    }

    @PutMapping("/update/city")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update User City", description = "Updates the city of the authenticated user.")
    UserDto updateCity(Authentication authentication, @RequestBody UpdateCityRequestDto updateCityRequestDto) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.updateCity(authentication, updateCityRequestDto);
    }

    @DeleteMapping("/reset/frameworks")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Reset User Frameworks", description = "Resets the frameworks list for the authenticated user.")
    UserDto resetFrameworks(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.resetFrameworks(authentication);
    }

    @DeleteMapping("/reset/programmingLanguages")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Reset User Programming Languages", description = "Resets the programming languages list for the authenticated user.")
    UserDto resetProgrammingLanguages(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.resetProgrammingLanguages(authentication);
    }

    @DeleteMapping("/reset/experienceLevel")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Reset User Experience Level", description = "Resets the experience level of the authenticated user.")
    UserDto resetExperienceLevel(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        return userService.resetExperienceLevel(authentication);
    }
}
