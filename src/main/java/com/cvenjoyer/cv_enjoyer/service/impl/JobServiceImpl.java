package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.exceptions.EntityNotFoundException;
import com.cvenjoyer.cv_enjoyer.mapper.JobMapper;
import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.repository.JobRepository;
import com.cvenjoyer.cv_enjoyer.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    @Override
    public JobDto createJob(CreateJobRequestDto createJobRequestDto) {
        Job newJob = jobMapper.toModel(createJobRequestDto);
        jobRepository.save(newJob);
        return jobMapper.toDto(newJob);
    }

    @Override
    public List<JobDto> getAllJobs() {
       return jobRepository.findAll()
                .stream()
                .map(jobMapper::toDto)
               .collect(Collectors.toList());
    }

    @Override
    public JobDto updateJob(UpdateJobRequestDto updateJobRequestDto, Long id) {
       Job actualJob = jobRepository.findById(id).orElseThrow(
               () -> new EntityNotFoundException("Entity not found by ID: " + id));
        Job updatedJob = jobMapper.updateJob(updateJobRequestDto, actualJob);
        return jobMapper.toDto(updatedJob);
    }
}
