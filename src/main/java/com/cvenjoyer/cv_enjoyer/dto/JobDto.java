package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Job;
import java.math.BigDecimal;
import java.time.LocalDate;

public record JobDto(
        Long id,
        String companyName,
        String position,
        String location,
        BigDecimal salary,
        LocalDate applicationDate,
        Job.JobStatus jobStatus,
        Job.JobType jobType,
        LocalDate feedBackDate,
        String link,
        String companyWebsite,
        String contactEmail
) {
}
