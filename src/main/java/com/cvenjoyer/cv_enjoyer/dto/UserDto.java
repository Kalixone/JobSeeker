package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Role;
import java.util.Set;

public record UserDto(
        String email,
        String firstName,
        String lastName,
        String city,
        Set<Role> roles,
        String englishLevel,
        Set<String> frameworks,
        Set<String> programmingLanguages,
        Set<String> experienceLevel

) {
}
