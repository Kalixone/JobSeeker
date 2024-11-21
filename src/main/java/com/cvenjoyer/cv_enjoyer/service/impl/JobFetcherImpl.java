package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.RemoteApiJobDto;
import com.cvenjoyer.cv_enjoyer.mapper.JobMapper;
import com.cvenjoyer.cv_enjoyer.model.RemoteApiJob;
import com.cvenjoyer.cv_enjoyer.model.RemoteApiResponse;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.RemoteApiJobRepository;
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
    private final RemoteApiJobRepository remoteApiJobRepository;
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

        RemoteApiResponse response = restTemplate.getForObject(url, RemoteApiResponse.class);

        if (response != null && response.getJobs() != null) {
            response.getJobs().forEach(jobData -> {
                RemoteApiJob remoteApiJob = new RemoteApiJob();
                remoteApiJob.setPosition(jobData.getPosition());
                remoteApiJob.setCompanyName(jobData.getCompanyName());
                remoteApiJob.setLink(jobData.getLink());
                remoteApiJob.setCategory(jobData.getCategory());
                List<String> frameworksInJob = jobData.getFrameworks();
                remoteApiJob.setFrameworks(frameworksInJob);
                remoteApiJob.setJobType(jobData.getJobType());
                remoteApiJob.setPublicationDate(jobData.getPublicationDate());
                remoteApiJob.setCandidateRequiredLocation(jobData.getCandidateRequiredLocation());

                remoteApiJobRepository.save(remoteApiJob);
            });
        }

        return remoteApiJobRepository.findAll().stream()
                .map(jobMapper::toRemoteApiJobDto)
                .collect(Collectors.toList());
    }
}
