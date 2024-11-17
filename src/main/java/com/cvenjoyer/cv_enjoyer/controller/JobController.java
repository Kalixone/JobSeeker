package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateFeedBackDateRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobStatusRequestDto;
import com.cvenjoyer.cv_enjoyer.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/jobs")
public class JobController {
    private final JobService jobService;

    @PostMapping
    public JobDto createJob(@RequestBody CreateJobRequestDto createJobRequestDto) {
        return jobService.createJob(createJobRequestDto);
    }

    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }


    @PutMapping("/{id}/feedback")
    public JobDto updateFeedBackDate(@PathVariable Long id, @RequestBody UpdateFeedBackDateRequestDto updateFeedBackDateRequestDto) {
        return jobService.updateFeedBackDate(id, updateFeedBackDateRequestDto);
    }

    @GetMapping("/{id}")
    public JobDto findById(@PathVariable Long id) {
        return jobService.findById(id);
    }

    @PutMapping("/{id}/status")
    public JobDto updateJobStatus(@PathVariable Long id, @RequestBody UpdateJobStatusRequestDto updateJobStatusRequestDto) {
        return jobService.updateJobStatus(id, updateJobStatusRequestDto);
    }
}
