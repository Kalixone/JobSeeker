package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.validation.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserLoginRequestDto(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Length(min = 8, max = 20)
        String password
) {
}