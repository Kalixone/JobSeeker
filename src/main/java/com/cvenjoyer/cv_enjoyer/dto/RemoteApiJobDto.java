package com.cvenjoyer.cv_enjoyer.dto;

import java.time.LocalDate;
import java.util.Set;

public record RemoteApiJobDto(
        Long id,
        String position,
        String companyName,
        String link,
        String category,
        Set<String> tags,
        String jobType,
        LocalDate publicationDate,
        String candidateRequiredLocation,
        String salary
) {
}
