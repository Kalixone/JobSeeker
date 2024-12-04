package com.cvenjoyer.cv_enjoyer.dto;

import java.time.LocalDate;

public record UpdateInterviewDateRequestDto(
        LocalDate interviewDate
) {
}
