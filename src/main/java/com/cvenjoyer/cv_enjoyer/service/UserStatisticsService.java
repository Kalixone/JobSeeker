package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.model.Job;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public interface UserStatisticsService {
    Long totalApplications(Authentication authentication);
    Long expiredJobsCount(Authentication authentication);
    Long rejectedJobsCount(Authentication authentication);
    Long appliedJobsCount(Authentication authentication);
    Job.JobType preferredJobType(Authentication authentication);
    Long countByUserAndApplicationDateBetween(Authentication authentication, LocalDate startDate, LocalDate endDate);
}
