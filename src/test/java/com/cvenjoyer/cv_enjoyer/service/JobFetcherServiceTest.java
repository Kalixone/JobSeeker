package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.RemoteApiJobDto;
import com.cvenjoyer.cv_enjoyer.mapper.JobMapper;
import com.cvenjoyer.cv_enjoyer.model.*;
import com.cvenjoyer.cv_enjoyer.repository.JobApiRepository;
import com.cvenjoyer.cv_enjoyer.service.impl.JobFetcherImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JobFetcherServiceTest {
    private static final String POSITION = "Java Developer";
    private static final String COMPANY_NAME = "Tech Corp";
    private static final String LINK = "http://example.com";
    private static final String CATEGORY = "Development";
    private static final Set<String> TAGS = Set.of("Java", "Spring");
    private static final String JOB_TYPE = "full_time";
    private static final LocalDate PUBLICATION_DATE = LocalDate.now();
    private static final String CANDIDATE_REQUIRED_LOCATION = "Worldwide";
    private static final String SALARY = "5000-7000 USD";
    private static final String EXPECTED_URL = "https://remotive.io/api/remote-jobs?tags=Java";
    private static final String LOCATION = "Kraków";

    @Mock
    private RestTemplate restTemplate;
    @Mock
    private JobApiRepository remoteApiJobRepository;
    @Mock
    private JobMapper jobMapper;
    @InjectMocks
    private JobFetcherImpl jobFetcher;


    @Test
    @DisplayName("fetchAndSaveJobsFromRemotiveApi returns list of fetched jobs and saves them")
    void fetchAndSaveJobsFromRemotiveApi_ReturnsListOfFetchedJobsAndSavesThem() {
        Authentication authentication = mock(Authentication.class);
        User mockUser = new User();
        mockUser.setProgrammingLanguages(Set.of("Java"));
        mockUser.setFrameworks(Set.of("Spring"));
        when(authentication.getPrincipal()).thenReturn(mockUser);

        RemotiveJobResponse mockResponse = new RemotiveJobResponse();
        RemotiveJob mockJob = buildMockJob();
        mockResponse.setJobs(List.of(mockJob));

        when(restTemplate.getForObject(EXPECTED_URL, RemotiveJobResponse.class)).thenReturn(mockResponse);

        JobApi jobApi = new JobApi();
        jobApi.setPosition(POSITION);
        when(remoteApiJobRepository.saveAll(anyList())).thenReturn(List.of(jobApi));
        when(remoteApiJobRepository.findAll()).thenReturn(List.of(jobApi));

        RemoteApiJobDto jobDto = buildMockJobDto(1L);

        when(jobMapper.toRemoteApiJobDto(any())).thenReturn(jobDto);

        List<RemoteApiJobDto> result = jobFetcher.fetchAndSaveJobsFromRemotiveApi(authentication);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(POSITION, result.get(0).position());
        assertEquals(COMPANY_NAME, result.get(0).companyName());

        verify(restTemplate, times(1)).getForObject(EXPECTED_URL, RemotiveJobResponse.class);
        verify(remoteApiJobRepository).saveAll(anyList());
        verify(remoteApiJobRepository).findAll();
    }

    @Test
    @DisplayName("searchByCompanyName returns list of jobs by company name")
    void searchByCompanyName_ReturnsListOfJobsByCompanyName() {
        JobApi jobApi = buildMockJobApi();
        List<JobApi> jobApis = List.of(jobApi);
        RemoteApiJobDto jobDto = buildMockJobDto(1L);

        when(remoteApiJobRepository.findByCompanyNameIgnoreCaseContaining(COMPANY_NAME)).thenReturn(jobApis);
        when(jobMapper.toRemoteApiJobDto(any())).thenReturn(jobDto);

        List<RemoteApiJobDto> result = jobFetcher.searchByCompanyName(COMPANY_NAME);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(POSITION, result.get(0).position());
        verify(remoteApiJobRepository).findByCompanyNameIgnoreCaseContaining(COMPANY_NAME);
        verify(jobMapper).toRemoteApiJobDto(any());
    }

    @Test
    @DisplayName("searchByPosition returns list of jobs by position")
    void searchByPosition_ReturnsListOfJobsByPosition() {
        JobApi jobApi = buildMockJobApi();
        List<JobApi> jobApis = List.of(jobApi);
        RemoteApiJobDto jobDto = buildMockJobDto(1L);

        when(remoteApiJobRepository.findByPositionIgnoreCaseContaining(POSITION)).thenReturn(jobApis);
        when(jobMapper.toRemoteApiJobDto(any())).thenReturn(jobDto);

        List<RemoteApiJobDto> result = jobFetcher.searchByPosition(POSITION);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(COMPANY_NAME, result.get(0).companyName());
        verify(remoteApiJobRepository).findByPositionIgnoreCaseContaining(POSITION);
        verify(jobMapper).toRemoteApiJobDto(any());
    }

    @Test
    @DisplayName("searchByTags returns list of jobs by tags")
    void searchByTags_ReturnsListOfJobsByTags() {
        JobApi jobApi = buildMockJobApi();
        List<JobApi> jobApis = List.of(jobApi);
        RemoteApiJobDto jobDto = buildMockJobDto(1L);

        when(remoteApiJobRepository.findByTagsContaining(TAGS)).thenReturn(jobApis);
        when(jobMapper.toRemoteApiJobDto(any())).thenReturn(jobDto);

        List<RemoteApiJobDto> result = jobFetcher.searchByTags(TAGS);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(CANDIDATE_REQUIRED_LOCATION, result.get(0).candidateRequiredLocation());
        verify(remoteApiJobRepository).findByTagsContaining(TAGS);
        verify(jobMapper).toRemoteApiJobDto(any());
    }

    @Test
    @DisplayName("findByJobType returns list of jobs by job type")
    void findByJobType_ReturnsListOfJobsByJobType()  {
        JobApi jobApi = buildMockJobApi();
        List<JobApi> jobApis = List.of(jobApi);
        RemoteApiJobDto jobDto = buildMockJobDto(1L);
        Job.JobType jobType = Job.JobType.FULL_TIME;

        when(remoteApiJobRepository.findByJobType(jobType)).thenReturn(jobApis);
        when(jobMapper.toRemoteApiJobDto(any())).thenReturn(jobDto);

        List<RemoteApiJobDto> result = jobFetcher.findByJobType(jobType);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(POSITION, result.get(0).position());
        verify(remoteApiJobRepository).findByJobType(jobType);
        verify(jobMapper).toRemoteApiJobDto(any());
    }

    @Test
    @DisplayName("findByPublicationDateBetween returns list of jobs by date range")
    void findByPublicationDateBetween_ReturnsListOfJobsByDateRange()  {
        JobApi jobApi = buildMockJobApi();
        List<JobApi> jobApis = List.of(jobApi);
        RemoteApiJobDto jobDto = buildMockJobDto(1L);
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        when(remoteApiJobRepository.findByPublicationDateBetween(startDate, endDate)).thenReturn(jobApis);
        when(jobMapper.toRemoteApiJobDto(any())).thenReturn(jobDto);

        List<RemoteApiJobDto> result = jobFetcher.findByPublicationDateBetween(startDate, endDate);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(COMPANY_NAME, result.get(0).companyName());
        verify(remoteApiJobRepository).findByPublicationDateBetween(startDate, endDate);
        verify(jobMapper).toRemoteApiJobDto(any());
    }

    private JobApi buildMockJobApi() {
        JobApi jobApi = new JobApi();
        jobApi.setPosition(POSITION);
        jobApi.setCompanyName(COMPANY_NAME);
        jobApi.setCandidateRequiredLocation(CANDIDATE_REQUIRED_LOCATION);
        jobApi.setTags(TAGS);
        jobApi.setJobType(Job.JobType.FULL_TIME);
        jobApi.setPublicationDate(PUBLICATION_DATE);
        return jobApi;
    }

    private RemotiveJob buildMockJob() {
        RemotiveJob mockJob = new RemotiveJob();
        mockJob.setPosition(POSITION);
        mockJob.setCompanyName(COMPANY_NAME);
        mockJob.setLink(LINK);
        mockJob.setCategory(CATEGORY);
        mockJob.setTags(List.copyOf(TAGS));
        mockJob.setJobType(JOB_TYPE);
        mockJob.setPublicationDate(PUBLICATION_DATE);
        mockJob.setCandidateRequiredLocation(CANDIDATE_REQUIRED_LOCATION);
        return mockJob;
    }

    private RemoteApiJobDto buildMockJobDto(Long id) {
        return new RemoteApiJobDto(
                id,
                POSITION,
                COMPANY_NAME,
                LINK,
                CATEGORY,
                TAGS,
                JOB_TYPE,
                PUBLICATION_DATE,
                CANDIDATE_REQUIRED_LOCATION,
                SALARY
        );
    }
}
