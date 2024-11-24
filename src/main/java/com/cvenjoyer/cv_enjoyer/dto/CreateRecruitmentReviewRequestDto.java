package com.cvenjoyer.cv_enjoyer.dto;


public record CreateRecruitmentReviewRequestDto(
        String companyName,
        String position,
        Integer stages,
        String review,
        Double rating
) {
}
