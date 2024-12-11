package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Job;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record CreateJobRequestDto(
        @NotBlank
        String companyName,
        @NotBlank
        String position,
        @NotBlank
        String location,
        BigDecimal salary,
        @NotNull
        LocalDate applicationDate,
        Job.JobType jobType,
        @NotBlank
        String link,
        String companyWebsite,
        String contactEmail,
        Set<String> tags,
        String notes
) {
}
