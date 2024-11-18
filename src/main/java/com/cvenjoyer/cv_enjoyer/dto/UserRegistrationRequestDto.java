package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record UserRegistrationRequestDto(
        @Email
        String email,
        @NotBlank
        @Length(min = 8, max = 20)
        String password,
        @NotBlank
        @Length(min = 8, max = 20)
        String repeatPassword,
        @NotNull
        String firstName,
        @NotNull
        String lastName,
        String city
) {
}
