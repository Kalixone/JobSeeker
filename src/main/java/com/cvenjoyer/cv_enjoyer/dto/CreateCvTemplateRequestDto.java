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
        String contactPhone,

        @NotBlank
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
