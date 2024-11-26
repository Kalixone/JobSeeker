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
    public List<JobDto> getAllJobs(Authentication authentication) {
        return jobService.getAllJobs(authentication);
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

    @PutMapping("/{id}/companyName")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update company name", description = "Update the company name for a specific job by its ID.")
    public JobDto updateCompanyName(@PathVariable Long id,
                                  @RequestBody @Valid UpdateCompanyNameRequestDto updateCompanyNameRequestDto) {
        return jobService.updateCompanyName(id, updateCompanyNameRequestDto);
    }

    @PutMapping("/{id}/position")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Job Position", description = "Updates the position/title of a specific job based on the provided details.")
    public JobDto updatePostion(@PathVariable Long id,
                                    @RequestBody @Valid UpdatePositionRequestDto updatePositionRequestDto) {
        return jobService.updatePosition(id, updatePositionRequestDto);
    }

    @PutMapping("/{id}/location")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Job Location", description = "Updates the location of a specific job.")
    public JobDto updateLocation(Authentication authentication, @PathVariable Long id,
                                @RequestBody @Valid UpdateLocationRequestDto updateLocationRequestDto) {
        return jobService.updateLocation(authentication, id, updateLocationRequestDto);
    }

    @PutMapping("/{id}/salary")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Job Salary", description = "Updates the salary offered for a specific job.")
    public JobDto updateSalary(@PathVariable Long id,
                                 @RequestBody @Valid UpdateSalaryRequestDto updateSalaryRequestDto) {
        return jobService.updateSalary(id, updateSalaryRequestDto);
    }

    @PutMapping("/{id}/applicationDate")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Job Application Date", description = "Updates the application deadline for a specific job.")
    public JobDto updateApplicationDate(@PathVariable Long id,
                               @RequestBody @Valid UpdateApplicationDateRequestDto updateApplicationDateRequestDto) {
        return jobService.updateApplicationDate(id, updateApplicationDateRequestDto);
    }

    @PutMapping("/{id}/jobType")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Job Type", description = "Updates the job type (e.g., full-time, part-time) for a specific job.")
    public JobDto updateJobType(@PathVariable Long id,
                                        @RequestBody @Valid UpdateJobTypeRequestDto updateJobTypeRequestDto) {
        return jobService.updateJobType(id, updateJobTypeRequestDto);
    }

    @PutMapping("/{id}/link")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Job Link", description = "Updates the link to the job posting.")
    public JobDto updateLink(@PathVariable Long id,
                                @RequestBody @Valid UpdateLinkRequestDto updateLinkRequestDto) {
        return jobService.updateLink(id, updateLinkRequestDto);
    }

    @PutMapping("/{id}/companyWebsite")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Company Website", description = "Updates the company's website link associated with a specific job.")
    public JobDto updateCompanyWebsite(@PathVariable Long id,
                             @RequestBody @Valid UpdateCompanyWebsiteRequestDto updateCompanyWebsiteRequestDto) {
        return jobService.updateCompanyWebsite(id, updateCompanyWebsiteRequestDto);
    }

    @PutMapping("/{id}/contactEmail")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Contact Email", description = "Updates the contact email address for a specific job posting.")
    public JobDto updateContactEmail(@PathVariable Long id,
                                       @RequestBody @Valid UpdateContactEmailRequestDto updateContactEmailRequestDto) {
        return jobService.updateContactEmail(id, updateContactEmailRequestDto);
    }

    @PutMapping("/{id}/kilometers")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Job Distance", description = "Updates the distance (in kilometers) for a specific job offer.")
    public JobDto updateKilometers(@PathVariable Long id,
                                     @RequestBody @Valid UpdateKilometersRequestDto updateKilometersRequestDto) {
        return jobService.updateKilometers(id, updateKilometersRequestDto);
    }

    @PutMapping("/{id}/notes")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Job Notes", description = "Updates the notes section for a specific job offer.")
    public JobDto updateNotes(@PathVariable Long id,
                                   @RequestBody @Valid UpdateNotesRequestDto updateNotesRequestDto) {
        return jobService.updateNotes(id, updateNotesRequestDto);
    }

    @PutMapping("/{id}/tags")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Job Tags", description = "Updates the tags associated with a specific job to help with categorization or search.")
    public JobDto updateTags(@PathVariable Long id,
                              @RequestBody @Valid UpdateTagsRequestDto updateTagsRequestDto) {
        return jobService.updateTags(id, updateTagsRequestDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Delete Job Offer", description = "Deletes a specific job offer based on its ID.")
    public void deleteById(@PathVariable Long id) {
        jobService.deleteById(id);
    }
}
