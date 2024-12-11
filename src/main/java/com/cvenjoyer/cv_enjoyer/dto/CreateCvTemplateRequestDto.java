package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateCvTemplateRequestDto(
        @NotBlank
        String name,

        @NotBlank
        String title,

        @NotBlank
        String profileDescription,

        @NotBlank
        @Pattern(regexp = "^(\\+\\d{1,2}\\s?)?\\(?\\d{2,3}\\)?[\\s\\-]?\\d{3}[\\s\\-]?\\d{2}[\\s\\-]?\\d{2}$", message = "Invalid phone number format")
        String contactPhone,

        @NotBlank
        @Pattern(regexp = "^[\\w-]+(?:\\.[\\w-]+)*@[\\w-]+(?:\\.[\\w-]+)+$", message = "Invalid email format")
        String contactEmail,

        @NotBlank
        String linkedin,

        @NotBlank
        String github,

        @NotBlank
        String education,

        @NotBlank
        String skills,

        @NotBlank
        String experience,

        String fileName
) {
}
