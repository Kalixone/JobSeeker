package com.cvenjoyer.cv_enjoyer.dto;

public record CreateCvTemplateRequestDto(
        String name,
        String title,
        String profileDescription,
        String contactPhone,
        String contactEmail,
        String linkedin,
        String github,
        String education,
        String skills,
        String experience,
        String fileName
) {}
