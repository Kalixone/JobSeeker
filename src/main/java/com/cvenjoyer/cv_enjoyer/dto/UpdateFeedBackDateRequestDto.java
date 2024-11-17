package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record UpdateFeedBackDateRequestDto(
        @NotBlank
        LocalDate feedBackDate
) {
}
