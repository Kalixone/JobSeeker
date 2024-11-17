package com.cvenjoyer.cv_enjoyer.dto;

import com.cvenjoyer.cv_enjoyer.model.Job;

public record UpdateJobStatusRequestDto(
        Job.JobStatus jobStatus
) {
}
