package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.UserLoginRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UserLoginResponseDto;
import com.cvenjoyer.cv_enjoyer.dto.UserRegistrationRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UserRegistrationResponseDto;
import com.cvenjoyer.cv_enjoyer.exceptions.RegistrationException;
import com.cvenjoyer.cv_enjoyer.security.AuthenticationService;
import com.cvenjoyer.cv_enjoyer.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    @Operation(summary = "User Registration", description = "Allows a new user to register with the system using provided user details.")
    public UserRegistrationResponseDto registerUser
            (@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    @Operation(summary = "User Login", description = "Authenticate and log in an existing user.")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
