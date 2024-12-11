package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateRecruitmentReviewRequestDto(
        @NotBlank
        String companyName,
        @NotBlank
        String position,
        @NotNull
        @Min(value = 1, message = "Stages must be at least 1")
        Integer stages,
        @NotBlank
        String review,
        @NotNull
        @Min(value = 1, message = "Rating must be at least 1.0")
        @Max(value = 5, message = "Rating must be at most 5.0")
        Double rating
) {
}
