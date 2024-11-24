package com.cvenjoyer.cv_enjoyer.dto;

import java.time.LocalDateTime;

public record RecruitmentReviewDto(
        Long id,
        String companyName,
        String position,
        Integer stages,
        String review,
        Double rating,
        LocalDateTime reviewDate
) {
}
