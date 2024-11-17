package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.model.Job;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface JobService {
    Job createJob(CreateJobRequestDto createJobRequestDto);
    List<Job> getAllJobs();
    void updateJob(UpdateJobRequestDto updateJobRequestDto);
}
