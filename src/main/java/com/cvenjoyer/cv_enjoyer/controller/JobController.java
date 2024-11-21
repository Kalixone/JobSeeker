package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.*;
import com.cvenjoyer.cv_enjoyer.service.JobService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/jobs")
@Tag(name = "Job Management", description = "API for managing job applications")
public class JobController {
    private final JobService jobService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Create a new job", description = "Allows the user to create a new job with all necessary details.")
    public JobDto createJob(Authentication authentication, @RequestBody @Valid CreateJobRequestDto createJobRequestDto) {
        return jobService.createJob(authentication, createJobRequestDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Retrieve all jobs", description = "Fetch a list of all jobs.")
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }


    @PutMapping("/{id}/feedback")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update feedback date", description = "Updates the feedback date for a specific job by its ID.")
    public JobDto updateFeedBackDate(@PathVariable Long id,
                                     @RequestBody @Valid UpdateFeedBackDateRequestDto updateFeedBackDateRequestDto) {
        return jobService.updateFeedBackDate(id, updateFeedBackDateRequestDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get job by ID", description = "Fetch details of a job using its ID.")
    public JobDto findById(@PathVariable Long id) {
        return jobService.findById(id);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update job status", description = "Updates the status of a specific job by its ID.")
    public JobDto updateJobStatus(@PathVariable Long id,
                                  @RequestBody @Valid UpdateJobStatusRequestDto updateJobStatusRequestDto) {
        return jobService.updateJobStatus(id, updateJobStatusRequestDto);
    }

    @GetMapping("/filtr")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get jobs sorted by salary", description = "Fetch a list of jobs sorted by salary in ascending order.")
    public List<JobDto> getJobsSortedBySalary() {
        return jobService.getJobsSortedBySalary();
    }

    @GetMapping("/applied")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get applied jobs", description = "Fetch a list of jobs with the status 'APPLIED'.")
    public List<JobDto> getOnlyAppliedStatus() {
        return jobService.getOnlyAppliedStatus();
    }

    @GetMapping("/rejected")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get rejected jobs", description = "Fetch a list of jobs with the status 'REJECTED'.")
    public List<JobDto> getOnlyRejectedStatus() {
        return jobService.getOnlyRejectedStatus();
    }

    @GetMapping("/expired")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get expired jobs", description = "Fetch a list of jobs with the status 'EXPIRED'.")
    public List<JobDto> getOnlyExpiredStatus() {
        return jobService.getOnlyExpiredStatus();
    }

    @GetMapping("/findByKilometers")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Find jobs by kilometers range", description = "Fetch jobs where kilometers are between the specified 'from' and 'to' values.")
    public List<JobDto> findByKilometersBetween(@RequestParam Double from, @RequestParam Double to) {
        return jobService.findByKilometersBetween(from, to);
    }
}
