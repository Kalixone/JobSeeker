package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Job;
import java.time.LocalDate;
import java.util.Set;
public record JobDto(
        Long id,
        String companyName,
        String position,
        String location,
        String salary,
        LocalDate applicationDate,
        LocalDate feedBackDate,
        Job.JobStatus jobStatus,
        Job.JobType jobType,
        String link,
        String companyWebsite,
        String contactEmail,
        Double kilometers,
        Set<String> tags,
        String notes,
        Integer dailyGoal
) {
}
