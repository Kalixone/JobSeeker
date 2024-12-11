package com.cvenjoyer.cv_enjoyer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;

public record UpdateTagsRequestDto(
        @NotEmpty
        @NotNull
        Set<@NotBlank String> tags
) {
}
