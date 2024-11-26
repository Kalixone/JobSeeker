package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.RemoteApiJobDto;
import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.service.JobFetcherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/remote-api-jobs")
@Tag(name = "Remote API Job Management", description = "API for managing jobs fetched from external remote job API")
public class RemoteApiJobController {

    private final JobFetcherService jobFetcher;

    @GetMapping("/remotive")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get all jobs from remote API", description = "Fetch a list of all jobs saved from the Remotive API.")
    public List<RemoteApiJobDto> getAllJobsFromRemotiveApi(Authentication authentication) {
        return jobFetcher.fetchAndSaveJobsFromRemotiveApi(authentication);
    }

    @GetMapping("apply/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Apply for a job", description = "Allows a user to apply for a job by providing the job ID. The user must be authenticated to apply.")
    public JobDto apply(@PathVariable Long id, Authentication authentication) {
        return jobFetcher.apply(id, authentication);
    }

    @GetMapping("/search/company")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search jobs by company name", description = "Search jobs based on the company name.")
    public List<RemoteApiJobDto> searchByCompanyName(@RequestParam String companyName) {
        return jobFetcher.searchByCompanyName(companyName);
    }

    @GetMapping("/search/position")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search jobs by position", description = "Search jobs based on the job position.")
    public List<RemoteApiJobDto> searchByPosition(@RequestParam String position) {
        return jobFetcher.searchByPosition(position);
    }

    @GetMapping("/search/tags")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search jobs by tags", description = "Search jobs based on tags (e.g. frameworks, technologies).")
    public List<RemoteApiJobDto> searchByTags(@RequestParam String tags) {
        Set<String> tagSet = Arrays.stream(tags.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());

        return jobFetcher.searchByTags(tagSet);
    }

    @GetMapping("/search/jobType")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search jobs by job type", description = "Search jobs based on job type (e.g. full-time, contract, remote).")
    public List<RemoteApiJobDto> findByJobType(@RequestParam Job.JobType jobType) {
        return jobFetcher.findByJobType(jobType);
    }

    @GetMapping("/search/applicationDate")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search jobs by application date range", description = "Search jobs based on application date range.")
    public List<RemoteApiJobDto> findByApplicationDateBetween(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return jobFetcher.findByPublicationDateBetween(startDate, endDate);
    }

    @GetMapping("/search/location")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search jobs by candidate required location", description = "Search jobs based on candidate required location.")
    public List<RemoteApiJobDto> findByCandidateRequiredLocation(@RequestParam String location) {
        return jobFetcher.findByCandidateRequiredLocationIgnoreCaseContaining(location);
    }
}
