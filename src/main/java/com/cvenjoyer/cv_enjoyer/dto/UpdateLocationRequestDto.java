package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateLocationRequestDto(
        @NotBlank
        String location
) {
}
