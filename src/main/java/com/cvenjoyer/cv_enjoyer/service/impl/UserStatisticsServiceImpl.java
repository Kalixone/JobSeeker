package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.JobRepository;
import com.cvenjoyer.cv_enjoyer.service.UserStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserStatisticsServiceImpl implements UserStatisticsService {
    private final JobRepository jobRepository;

    @Override
    public Long totalApplications(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return jobRepository.countByUser(principal);
    }

    @Override
    public Long expiredJobsCount(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return jobRepository.countByUserAndJobStatus(principal, Job.JobStatus.EXPIRED);
    }

    @Override
    public Long rejectedJobsCount(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return jobRepository.countByUserAndJobStatus(principal, Job.JobStatus.REJECTED);
    }

    @Override
    public Long appliedJobsCount(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return jobRepository.countByUserAndJobStatus(principal, Job.JobStatus.APPLIED);
    }

    @Override
    public Job.JobType preferredJobType(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        Long hybrid = jobRepository.countByUserAndJobType(principal, Job.JobType.HYBRID);
        Long remote = jobRepository.countByUserAndJobType(principal, Job.JobType.REMOTE);
        Long stationary = jobRepository.countByUserAndJobType(principal, Job.JobType.STATIONARY);

        if (hybrid >= remote && hybrid >= stationary) {
            return Job.JobType.HYBRID;
        } else if (remote >= hybrid && remote >= stationary) {
            return Job.JobType.REMOTE;
        }

        return Job.JobType.STATIONARY;
    }

    @Override
    public Long countByUserAndApplicationDateBetween(Authentication authentication, LocalDate startDate, LocalDate endDate) {
        User principal = (User) authentication.getPrincipal();
        return jobRepository.countByUserAndApplicationDateBetween(principal, startDate, endDate);
    }
}
