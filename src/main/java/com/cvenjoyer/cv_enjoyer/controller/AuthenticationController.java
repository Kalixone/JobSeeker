package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.UserLoginRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UserLoginResponseDto;
import com.cvenjoyer.cv_enjoyer.dto.UserRegistrationRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UserRegistrationResponseDto;
import com.cvenjoyer.cv_enjoyer.exceptions.RegistrationException;
import com.cvenjoyer.cv_enjoyer.security.AuthenticationService;
import com.cvenjoyer.cv_enjoyer.service.UserService;
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
    public UserRegistrationResponseDto registerUser
            (@RequestBody @Valid UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        return userService.register(requestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }
}
