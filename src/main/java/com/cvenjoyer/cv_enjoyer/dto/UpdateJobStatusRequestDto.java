package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Job;
import jakarta.validation.constraints.NotBlank;

public record UpdateJobStatusRequestDto(
        @NotBlank
        Job.JobStatus jobStatus
) {
}
