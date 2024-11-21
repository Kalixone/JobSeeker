package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.RemoteApiJobDto;
import com.cvenjoyer.cv_enjoyer.service.JobFetcher;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/remote-api-jobs")
@Tag(name = "Remote API Job Management", description = "API for managing jobs fetched from external remote job API")
public class RemoteApiJobController {

    private final JobFetcher jobFetcher;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get all jobs from remote API", description = "Fetch a list of all jobs saved from the Remotive API.")
    public List<RemoteApiJobDto> getAllJobs(Authentication authentication) {
        return jobFetcher.fetchAndSaveJobsFromRemotiveApi(authentication);
    }
}