package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Job;

public record UpdateJobTypeRequestDto(
        Job.JobType jobType
) {
}
