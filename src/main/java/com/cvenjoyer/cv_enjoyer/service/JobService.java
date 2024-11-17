package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateFeedBackDateRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobStatusRequestDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface JobService {
    JobDto createJob(CreateJobRequestDto createJobRequestDto);
    List<JobDto> getAllJobs();
    JobDto updateFeedBackDate(Long id, UpdateFeedBackDateRequestDto updateFeedBackDateRequestDto);
    JobDto findById(Long id);
    void checkAndExpireOldApplications();
    JobDto updateJobStatus(Long id, UpdateJobStatusRequestDto updateJobStatusRequestDto);
}
