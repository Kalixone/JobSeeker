package com.cvenjoyer.cv_enjoyer.dto;

import java.time.LocalDate;
import java.util.List;

public record RemoteApiJobDto(
        Long id,
        String position,
        String companyName,
        String link,
        String category,
        List<String> frameworks,
        String jobType,
        LocalDate publicationDate,
        String candidateRequiredLocation
) {
}
