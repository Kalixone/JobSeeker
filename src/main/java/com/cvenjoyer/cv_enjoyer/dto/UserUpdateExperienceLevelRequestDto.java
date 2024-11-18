package com.cvenjoyer.cv_enjoyer.dto;

import java.util.Set;

public record UserUpdateExperienceLevelRequestDto(
        Set<String> experienceLevel
) {
}
