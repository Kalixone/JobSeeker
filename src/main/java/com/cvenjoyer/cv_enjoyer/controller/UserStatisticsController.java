package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.service.UserStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/statistics")
public class UserStatisticsController {
    private final UserStatisticsService userStatisticsService;

    @GetMapping("/total-applications")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Total Applications", description = "Gets the total number of applications made by the authenticated user.")
    public Long totalApplications(Authentication authentication) {
        return userStatisticsService.totalApplications(authentication);
    }

    @GetMapping("/total-expired")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Expired Jobs", description = "Gets the total number of expired job listings for the authenticated user.")
    public Long expiredJobsCount(Authentication authentication) {
        return userStatisticsService.expiredJobsCount(authentication);
    }

    @GetMapping("/total-rejected")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Rejected Jobs", description = "Gets the total number of rejected job listings for the authenticated user.")
    public Long rejectedJobsCount(Authentication authentication) {
        return userStatisticsService.rejectedJobsCount(authentication);
    }

    @GetMapping("/total-applied")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Applied Jobs", description = "Gets the total number of jobs that the authenticated user has applied to.")
    public Long appliedJobsCount(Authentication authentication) {
        return userStatisticsService.appliedJobsCount(authentication);
    }

    @GetMapping("/preferred-jobType")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Preferred Job Type", description = "Gets the preferred job type for the authenticated user.")
    public Job.JobType preferredJobType(Authentication authentication) {
        return userStatisticsService.preferredJobType(authentication);
    }

    @GetMapping("/count-applications-in-date-range")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Applications in Date Range", description = "Gets the total number of applications made by the authenticated user within a specific date range.")
    public Long countApplicationsInDateRange(Authentication authentication, LocalDate startDate, LocalDate endDate) {
        return userStatisticsService.countByUserAndApplicationDateBetween(authentication, startDate, endDate);
    }
}
