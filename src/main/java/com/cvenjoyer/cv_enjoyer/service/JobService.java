package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateFeedBackDateRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobStatusRequestDto;
import com.cvenjoyer.cv_enjoyer.model.NominatimResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface JobService {
    JobDto createJob(Authentication authentication, CreateJobRequestDto createJobRequestDto);
    List<JobDto> getAllJobs(Authentication authentication);
    JobDto updateFeedBackDate(Long id, UpdateFeedBackDateRequestDto updateFeedBackDateRequestDto);
    JobDto findById(Long id);
    void checkAndExpireOldApplications();
    JobDto updateJobStatus(Long id, UpdateJobStatusRequestDto updateJobStatusRequestDto);
    List<JobDto> getJobsSortedBySalary();
    List<JobDto> getOnlyAppliedStatus();
    List<JobDto> getOnlyRejectedStatus();
    List<JobDto> getOnlyExpiredStatus();
    Double calculateDistance(double lat1, double lon1, double lat2, double lon2);
    NominatimResponse getLocation(String location);
    List<JobDto> findByKilometersBetween(Double from, Double to);
}
