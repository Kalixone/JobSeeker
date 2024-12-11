package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateContactEmailRequestDto(
        @NotBlank
        String contactEmail
) {
}
