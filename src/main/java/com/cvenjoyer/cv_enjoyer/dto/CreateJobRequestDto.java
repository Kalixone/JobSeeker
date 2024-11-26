package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Job;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
public record CreateJobRequestDto(
        @NotBlank(message = "Company name is required")
        String companyName,
        @NotBlank(message = "Position is required")
        String position,
        @NotBlank(message = "Location is required")
        String location,
        BigDecimal salary,
        @NotNull(message = "Application date is required")
        LocalDate applicationDate,
        Job.JobType jobType,
        @NotBlank(message = "Link is required")
        String link,
        String companyWebsite,
        String contactEmail,
        Set<String> tags,
        String notes
) {
}
