package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateFeedBackDateRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobStatusRequestDto;
import com.cvenjoyer.cv_enjoyer.exceptions.EntityNotFoundException;
import com.cvenjoyer.cv_enjoyer.mapper.JobMapper;
import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.repository.JobRepository;
import com.cvenjoyer.cv_enjoyer.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
        newJob.setJobStatus(Job.JobStatus.APPLIED);
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
    public JobDto updateFeedBackDate(Long id, UpdateFeedBackDateRequestDto updateFeedBackDateRequestDto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found by ID: " + id));

        if (updateFeedBackDateRequestDto.feedBackDate() != null) {
            job.setFeedBackDate(updateFeedBackDateRequestDto.feedBackDate());
        }

        jobRepository.save(job);

        return jobMapper.toDto(job);
    }

    @Override
    public JobDto findById(Long id) {
        return jobRepository.findById(id).map(jobMapper::toDto).orElseThrow(
                () -> new EntityNotFoundException("Entity not found by ID: " + id));
    }

    @Scheduled(cron = "0 0 0 * * *")
    @Override
    public void checkAndExpireOldApplications() {
        LocalDate now = LocalDate.now();

        jobRepository.findAll().stream()
                .filter(j -> j.getApplicationDate() != null &&
                        j.getApplicationDate().plusDays(14).isBefore(now))
                .forEach(j -> {
                    j.setJobStatus(Job.JobStatus.EXPIRED);
                    jobRepository.save(j);
                });
    }

    @Override
    public JobDto updateJobStatus(Long id, UpdateJobStatusRequestDto updateJobStatusRequestDto) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found by ID: " + id));

        if (updateJobStatusRequestDto.jobStatus() != null) {
            job.setJobStatus(updateJobStatusRequestDto.jobStatus());
        }
        jobRepository.save(job);
        return jobMapper.toDto(job);
    }
}
