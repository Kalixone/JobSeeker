package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Job;
import jakarta.validation.constraints.NotNull;

public record UpdateJobTypeRequestDto(
        @NotNull
        Job.JobType jobType
) {
}
