package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.repository.JobRepository;
import com.cvenjoyer.cv_enjoyer.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {
    private JobRepository jobRepository;

    @Override
    public Job createJob(CreateJobRequestDto createJobRequestDto) {

    }

    @Override
    public List<Job> getAllJobs() {
        return null;
    }

    @Override
    public void updateJob(UpdateJobRequestDto updateJobRequestDto) {

    }
}
