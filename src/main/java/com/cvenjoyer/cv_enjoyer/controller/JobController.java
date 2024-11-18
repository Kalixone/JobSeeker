package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateFeedBackDateRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobStatusRequestDto;
import com.cvenjoyer.cv_enjoyer.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/jobs")
@Tag(name = "Job Management", description = "API for managing job applications")
public class JobController {
    private final JobService jobService;

    @PostMapping
    @Operation(summary = "Create a new job", description = "Allows the user to create a new job with all necessary details.")
    public JobDto createJob(@RequestBody @Valid CreateJobRequestDto createJobRequestDto) {
        return jobService.createJob(createJobRequestDto);
    }

    @GetMapping
    @Operation(summary = "Retrieve all jobs", description = "Fetch a list of all jobs.")
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }


    @PutMapping("/{id}/feedback")
    @Operation(summary = "Update feedback date", description = "Updates the feedback date for a specific job by its ID.")
    public JobDto updateFeedBackDate(@PathVariable Long id,
                                     @RequestBody @Valid UpdateFeedBackDateRequestDto updateFeedBackDateRequestDto) {
        return jobService.updateFeedBackDate(id, updateFeedBackDateRequestDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get job by ID", description = "Fetch details of a job using its ID.")
    public JobDto findById(@PathVariable Long id) {
        return jobService.findById(id);
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update job status", description = "Updates the status of a specific job by its ID.")
    public JobDto updateJobStatus(@PathVariable Long id,
                                  @RequestBody @Valid UpdateJobStatusRequestDto updateJobStatusRequestDto) {
        return jobService.updateJobStatus(id, updateJobStatusRequestDto);
    }

    @GetMapping("/filtr")
    @Operation(summary = "Get jobs sorted by salary", description = "Fetch a list of jobs sorted by salary in ascending order.")
    public List<JobDto> getJobsSortedBySalary() {
        return jobService.getJobsSortedBySalary();
    }

    @GetMapping("/applied")
    @Operation(summary = "Get applied jobs", description = "Fetch a list of jobs with the status 'APPLIED'.")
    public List<JobDto> getOnlyAppliedStatus() {
        return jobService.getOnlyAppliedStatus();
    }

    @GetMapping("/rejected")
    @Operation(summary = "Get rejected jobs", description = "Fetch a list of jobs with the status 'REJECTED'.")
    public List<JobDto> getOnlyRejectedStatus() {
        return jobService.getOnlyRejectedStatus();
    }

    @GetMapping("/expired")
    @Operation(summary = "Get expired jobs", description = "Fetch a list of jobs with the status 'EXPIRED'.")
    public List<JobDto> getOnlyExpiredStatus() {
        return jobService.getOnlyExpiredStatus();
    }
}
