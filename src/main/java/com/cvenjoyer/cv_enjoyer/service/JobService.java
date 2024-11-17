package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobRequestDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface JobService {
    JobDto createJob(CreateJobRequestDto createJobRequestDto);
    List<JobDto> getAllJobs();
    JobDto updateJob(UpdateJobRequestDto updateJobRequestDto, Long id);
}
