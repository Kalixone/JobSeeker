package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record UpdateFeedBackDateRequestDto(
        @NotNull
        LocalDate feedBackDate
) {
}
