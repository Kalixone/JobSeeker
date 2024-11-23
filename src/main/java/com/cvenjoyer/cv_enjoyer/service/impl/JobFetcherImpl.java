package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.RemoteApiJobDto;
import com.cvenjoyer.cv_enjoyer.mapper.JobMapper;
import com.cvenjoyer.cv_enjoyer.model.JobApi;
import com.cvenjoyer.cv_enjoyer.model.RemotiveJob;
import com.cvenjoyer.cv_enjoyer.model.RemotiveJobResponse;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.JobApiRepository;
import com.cvenjoyer.cv_enjoyer.service.JobFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobFetcherImpl implements JobFetcher {

    private final JobApiRepository remoteApiJobRepository;
    private final JobMapper jobMapper;
    private final RestTemplate restTemplate;

    @Override
    public List<RemoteApiJobDto> fetchAndSaveJobsFromRemotiveApi(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();

        Set<String> programmingLanguages = principal.getProgrammingLanguages();
        Set<String> frameworks = principal.getFrameworks();

        Set<String> allSearchTags = new HashSet<>();
        allSearchTags.addAll(programmingLanguages);
        allSearchTags.addAll(frameworks);

        String searchQuery = String.join(",", allSearchTags);
        String url = "https://remotive.io/api/remote-jobs?tags=" + searchQuery;

        RemotiveJobResponse response = restTemplate.getForObject(url, RemotiveJobResponse.class);

        if (response != null && response.getJobs() != null) {
            List<RemotiveJob> remotiveJobs = response.getJobs();
            List<JobApi> jobApis = remotiveJobs.stream().map(result -> {
                JobApi jobApi = new JobApi();
                jobApi.setPosition(result.getPosition());
                jobApi.setCompanyName(result.getCompanyName());
                jobApi.setLink(result.getLink());
                jobApi.setCategory(result.getCategory());
                List<String> frameworks1 = result.getFrameworks();
                jobApi.setFrameworks(frameworks1);
                jobApi.setJobType(result.getJobType());
                jobApi.setPublicationDate(result.getPublicationDate());
                jobApi.setCandidateRequiredLocation(result.getCandidateRequiredLocation());
                return jobApi;
            }).collect(Collectors.toList());

            remoteApiJobRepository.saveAll(jobApis);
        }

            return remoteApiJobRepository.findAll().stream()
                    .map(jobMapper::toRemoteApiJobDto)
                    .collect(Collectors.toList());
        }
    }
