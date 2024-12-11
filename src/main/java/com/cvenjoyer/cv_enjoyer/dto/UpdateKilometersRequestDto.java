package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateKilometersRequestDto(
        @NotNull
        Double kilometers
) {
}
