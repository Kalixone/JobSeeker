package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.JobRepository;
import com.cvenjoyer.cv_enjoyer.service.impl.UserStatisticsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserStatisticsServiceTest {
    private JobRepository jobRepository;
    private UserStatisticsServiceImpl userStatisticsService;

    @Test
    @DisplayName("totalApplications returns the total number of job applications for the authenticated user")
    public void totalApplications_ReturnsTotalApplicationsForUser() {
        Authentication authentication = mock(Authentication.class);
        User user = mock(User.class);

        when(authentication.getPrincipal()).thenReturn(user);
        when(jobRepository.countByUser(user)).thenReturn(42L);

        Long totalApplications = userStatisticsService.totalApplications(authentication);

        assertThat(totalApplications).isEqualTo(42L);
        verify(authentication).getPrincipal();
        verify(jobRepository).countByUser(user);
        verifyNoMoreInteractions(authentication, jobRepository);
    }

    @Test
    @DisplayName("expiredJobsCount returns the number of expired job applications for the authenticated user")
    public void expiredJobsCount_ReturnsExpiredApplicationsForUser() {
        Authentication authentication = mock(Authentication.class);
        User user = mock(User.class);

        when(authentication.getPrincipal()).thenReturn(user);
        when(jobRepository.countByUserAndJobStatus(user, Job.JobStatus.EXPIRED)).thenReturn(10L);

        Long expiredApplications = userStatisticsService.expiredJobsCount(authentication);
        assertThat(expiredApplications).isEqualTo(10L);
        verify(authentication).getPrincipal();
        verify(jobRepository).countByUserAndJobStatus(user, Job.JobStatus.EXPIRED);
        verifyNoMoreInteractions(authentication, jobRepository);
    }

    @Test
    @DisplayName("rejectedJobsCount returns the number of jobs with REJECTED status for the authenticated user")
    public void rejectedJobsCount_ReturnsNumberOfRejectedJobsForUser() {
        Authentication authentication = mock(Authentication.class);
        User user = mock(User.class);

        when(authentication.getPrincipal()).thenReturn(user);
        when(jobRepository.countByUserAndJobStatus(user, Job.JobStatus.REJECTED)).thenReturn(15L);

        Long rejectedApplications = userStatisticsService.rejectedJobsCount(authentication);

        assertThat(rejectedApplications).isEqualTo(15L);
        verify(authentication).getPrincipal();
        verify(jobRepository).countByUserAndJobStatus(user, Job.JobStatus.REJECTED);
        verifyNoMoreInteractions(authentication, jobRepository);
    }

    @Test
    @DisplayName("appliedJobsCount returns the number of jobs with APPLIED status for the authenticated user")
    public void appliedJobsCount_ReturnsNumberOfAppliedJobsForUser() {
        Authentication authentication = mock(Authentication.class);
        User user = mock(User.class);

        when(authentication.getPrincipal()).thenReturn(user);
        when(jobRepository.countByUserAndJobStatus(user, Job.JobStatus.APPLIED)).thenReturn(5L);

        Long appliedApplications = userStatisticsService.appliedJobsCount(authentication);

        assertThat(appliedApplications).isEqualTo(5L);
        verify(authentication).getPrincipal();
        verify(jobRepository).countByUserAndJobStatus(user, Job.JobStatus.APPLIED);
        verifyNoMoreInteractions(authentication, jobRepository);
    }

    @Test
    @DisplayName("preferredJobType returns the job type with the highest count for the authenticated user")
    public void preferredJobType_ReturnsMostPreferredJobTypeForUser() {
        Authentication authentication = mock(Authentication.class);
        User user = mock(User.class);

        when(authentication.getPrincipal()).thenReturn(user);
        when(jobRepository.countByUserAndJobType(user, Job.JobType.HYBRID)).thenReturn(10L);
        when(jobRepository.countByUserAndJobType(user, Job.JobType.REMOTE)).thenReturn(15L);
        when(jobRepository.countByUserAndJobType(user, Job.JobType.STATIONARY)).thenReturn(5L);

        Job.JobType preferredJobType = userStatisticsService.preferredJobType(authentication);

        assertThat(preferredJobType).isEqualTo(Job.JobType.REMOTE);
        verify(authentication).getPrincipal();
        verify(jobRepository).countByUserAndJobType(user, Job.JobType.HYBRID);
        verify(jobRepository).countByUserAndJobType(user, Job.JobType.REMOTE);
        verify(jobRepository).countByUserAndJobType(user, Job.JobType.STATIONARY);
        verifyNoMoreInteractions(authentication, jobRepository);
    }
}
