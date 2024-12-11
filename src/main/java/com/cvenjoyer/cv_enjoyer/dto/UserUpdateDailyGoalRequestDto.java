package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDailyGoalRequestDto(
        @NotNull
        @Min(value = 1, message = "Daily goal must be at least 1")
        Integer dailyGoal
) {
}
