package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.RemoteApiJobDto;
import com.cvenjoyer.cv_enjoyer.model.Job;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public interface JobFetcherService {
    List<RemoteApiJobDto> fetchAndSaveJobsFromRemotiveApi(Authentication authentication);
    JobDto apply(Long id, Authentication authentication);
    List<RemoteApiJobDto> searchByCompanyName(String companyName);
    List<RemoteApiJobDto> searchByPosition(String position);
    List<RemoteApiJobDto> searchByTags(Set<String> tags);
    List<RemoteApiJobDto> findByJobType(Job.JobType jobType);
    List<RemoteApiJobDto> findByPublicationDateBetween(LocalDate startDate, LocalDate endDate);
    List<RemoteApiJobDto> findByCandidateRequiredLocationIgnoreCaseContaining(String location);
}
