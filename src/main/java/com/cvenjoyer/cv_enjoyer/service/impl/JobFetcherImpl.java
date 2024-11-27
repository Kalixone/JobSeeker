package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.RemoteApiJobDto;
import com.cvenjoyer.cv_enjoyer.exceptions.EntityNotFoundException;
import com.cvenjoyer.cv_enjoyer.mapper.JobMapper;
import com.cvenjoyer.cv_enjoyer.model.*;
import com.cvenjoyer.cv_enjoyer.repository.JobApiRepository;
import com.cvenjoyer.cv_enjoyer.repository.JobRepository;
import com.cvenjoyer.cv_enjoyer.repository.UserRepository;
import com.cvenjoyer.cv_enjoyer.service.JobFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobFetcherImpl implements JobFetcherService {

    private final JobApiRepository remoteApiJobRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
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

        List<RemotiveJob> allJobs = new ArrayList<>();

        for (String tag : allSearchTags) {
            String url = "https://remotive.io/api/remote-jobs?tags=" + tag;

            RemotiveJobResponse response = restTemplate.getForObject(url, RemotiveJobResponse.class);

            if (response != null && response.getJobs() != null) {
                List<RemotiveJob> remotiveJobs = response.getJobs();
                allJobs.addAll(remotiveJobs);
            }
        }

        allJobs = allJobs.stream()
                .distinct()
                .collect(Collectors.toList());

        List<JobApi> jobApis = allJobs.stream().map(result -> {
            JobApi jobApi = new JobApi();
            jobApi.setPosition(result.getPosition());
            jobApi.setCompanyName(result.getCompanyName());
            jobApi.setLink(result.getLink());
            jobApi.setCategory(result.getCategory());
            jobApi.setTags(new HashSet<>(result.getTags()));
            String jobType = result.getJobType();
            if (jobType != null) {
                switch (jobType.toLowerCase()) {
                    case "full_time":
                        jobApi.setJobType(Job.JobType.FULL_TIME);
                        break;
                    case "contract":
                        jobApi.setJobType(Job.JobType.CONTRACT);
                        break;
                    default:
                        jobApi.setJobType(Job.JobType.REMOTE);
                        break;
                }
            } else {
                jobApi.setJobType(Job.JobType.REMOTE);
            }
            jobApi.setSalary(result.getSalary());
            jobApi.setLocation("UNKNOWN");
            jobApi.setApplicationDate(LocalDate.now());
            jobApi.setPublicationDate(result.getPublicationDate());
            jobApi.setCandidateRequiredLocation(result.getCandidateRequiredLocation());
            return jobApi;
        }).collect(Collectors.toList());

        remoteApiJobRepository.saveAll(jobApis);

        return remoteApiJobRepository.findAll().stream()
                .map(jobMapper::toRemoteApiJobDto)
                .collect(Collectors.toList());
    }

    @Override
    public JobDto apply(Long id, Authentication authentication) {
        JobApi jobApi = remoteApiJobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found by ID: " + id));
        User principal = (User) authentication.getPrincipal();
        Job model = jobMapper.toModel(jobApi);
        model.setUser(principal);
        model.setJobStatus(Job.JobStatus.APPLIED);
        Integer dailyGoal = principal.getDailyGoal();
        if (dailyGoal != null && dailyGoal > 0) {
            principal.decrementDailyGoal();
            userRepository.save(principal);
        }
        jobRepository.save(model);
        return jobMapper.toDto(model);
    }

    @Override
    public List<RemoteApiJobDto> searchByCompanyName(String companyName) {
        List<JobApi> byCompanyName = remoteApiJobRepository.findByCompanyNameIgnoreCaseContaining(companyName);
        return byCompanyName.stream().map(jobMapper::toRemoteApiJobDto).collect(Collectors.toList());
    }

    @Override
    public List<RemoteApiJobDto> searchByPosition(String position) {
        List<JobApi> byPosition = remoteApiJobRepository.findByPositionIgnoreCaseContaining(position);
        return byPosition.stream().map(jobMapper::toRemoteApiJobDto).collect(Collectors.toList());
    }

    @Override
    public List<RemoteApiJobDto> searchByTags(Set<String> tags) {
        List<JobApi> byTagsContaining = remoteApiJobRepository.findByTagsContaining(tags);
        return byTagsContaining.stream()
                .map(jobMapper::toRemoteApiJobDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RemoteApiJobDto> findByJobType(Job.JobType jobType) {
        List<JobApi> byJobType = remoteApiJobRepository.findByJobType(jobType);
        return byJobType.stream().map(jobMapper::toRemoteApiJobDto).collect(Collectors.toList());
    }

    @Override
    public List<RemoteApiJobDto> findByPublicationDateBetween(LocalDate startDate, LocalDate endDate) {
        List<JobApi> byApplicationDateBetween = remoteApiJobRepository.findByPublicationDateBetween(startDate, endDate);
        return byApplicationDateBetween.stream().map(jobMapper::toRemoteApiJobDto).collect(Collectors.toList());
    }

    @Override
    public List<RemoteApiJobDto> findByCandidateRequiredLocationIgnoreCaseContaining(String location) {
        List<JobApi> byCandidateRequiredLocationIgnoreCaseContaining = remoteApiJobRepository.findByCandidateRequiredLocationIgnoreCaseContaining(location);
        return byCandidateRequiredLocationIgnoreCaseContaining.stream().map(jobMapper::toRemoteApiJobDto).collect(Collectors.toList());
    }
}
