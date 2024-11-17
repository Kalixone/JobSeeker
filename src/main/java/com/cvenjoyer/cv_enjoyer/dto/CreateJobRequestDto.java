package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Job;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateJobRequestDto(
        @NotBlank
        String companyName,
        @NotBlank
        String position,
        @NotBlank
        String location,
        BigDecimal salary,
        @NotBlank
        LocalDate applicationDate,
        Job.JobType jobType,
        @NotBlank
        String link,
        String companyWebsite,
        String contactEmail
) {
}
