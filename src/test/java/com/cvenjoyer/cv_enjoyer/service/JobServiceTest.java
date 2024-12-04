//package com.cvenjoyer.cv_enjoyer.service;
//
//import com.cvenjoyer.cv_enjoyer.dto.*;
//import com.cvenjoyer.cv_enjoyer.mapper.JobMapper;
//import com.cvenjoyer.cv_enjoyer.model.Job;
//import com.cvenjoyer.cv_enjoyer.model.User;
//import com.cvenjoyer.cv_enjoyer.repository.JobRepository;
//import com.cvenjoyer.cv_enjoyer.repository.UserRepository;
//import com.cvenjoyer.cv_enjoyer.service.impl.JobServiceImpl;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.core.Authentication;
//import java.time.LocalDate;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class JobServiceTest {
//    private static final Long JOB_ID = 1L;
//    private static final String COMPANY_NAME = "Police";
//    private static final String UPDATED_COMPANY_NAME = "Allegro";
//    private static final String POSITION = "Java Developer";
//    private static final String UPDATED_POSITION = "Python Developer";
//    private static final String LOCATION = "Warsaw";
//    private static final String SALARY = "2500.0";
//    private static final String UPDATED_SALARY = "3500.0";
//    private static final LocalDate APPLICATION_DATE = LocalDate.now();
//    private static final LocalDate UPDATED_APPLICATION_DATE = LocalDate.now();
//    private static final Job.JobStatus JOB_STATUS = Job.JobStatus.REJECTED;
//    private static final Job.JobType JOB_TYPE = Job.JobType.STATIONARY;
//    private static final LocalDate FEED_BACK_DATE = LocalDate.now();
//    private static final LocalDate UPDATED_FEED_BACK_DATE = LocalDate.now();
//    private static final String LINK = "www.google.com";
//    private static final String UPDATED_LINK = "www.cda.pl";
//    private static final String COMPANY_WEBSITE = "www.hejho.com";
//    private static final String CONTACT_EMAIL = "piotr.kamil.kaliszuk@gmail.com";
//    private static final Double KILOMETERS = 240.0;
//    private static final Set<String> TAGS = Set.of("java", "python");
//    private static final String NOTES = "Fine job";
//    private static final Long USER_ID = 1L;
//    private static final String FIRST_NAME = "Pioter";
//    private static final String LAST_NAME = "Kaliszuk";
//    private static final Integer DAILY_GOAL = 5;
//
//    @InjectMocks
//    private JobServiceImpl jobService;
//    @Mock
//    private JobRepository jobRepository;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private JobMapper jobMapper;
//
//    @Test
//    @DisplayName("getAllJobs with valid authentication returns a list of JobDto")
//    public void getAllJobs_ValidAuthentication_ReturnsJobDtoList() {
//        Authentication authentication = mock(Authentication.class);
//        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, user);
//        JobDto jobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, DAILY_GOAL);
//
//        when(authentication.getPrincipal()).thenReturn(user);
//        when(jobRepository.findByUser(user)).thenReturn(List.of(job));
//        when(jobMapper.toDto(job)).thenReturn(jobDto);
//
//        List<JobDto> result = jobService.getAllJobs(authentication);
//
//        assertThat(result).containsExactly(jobDto);
//
//        verify(authentication).getPrincipal();
//        verify(jobRepository).findByUser(user);
//        verify(jobMapper).toDto(job);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("updateFeedBackDate with valid ID and request updates feedback date and returns updated JobDto")
//    public void updateFeedBackDate_ValidIdAndRequest_UpdatesFeedbackDateAndReturnsJobDto() {
//        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, user);
//        Job updatedJob = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, UPDATED_FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, user);
//
//        JobDto updatedJobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, UPDATED_FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, DAILY_GOAL);
//
//        UpdateFeedBackDateRequestDto updateFeedBackDateRequestDto = new UpdateFeedBackDateRequestDto(UPDATED_FEED_BACK_DATE);
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobRepository.save(job)).thenReturn(updatedJob);
//        when(jobMapper.toDto(updatedJob)).thenReturn(updatedJobDto);
//
//        JobDto result = jobService.updateFeedBackDate(JOB_ID, updateFeedBackDateRequestDto);
//
//        assertThat(result).isEqualTo(updatedJobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobRepository).save(job);
//        verify(jobMapper).toDto(updatedJob);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("findById with valid ID returns corresponding JobDto")
//    public void findById_ValidId_ReturnsJobDto(){
//        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, user);
//        JobDto jobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, DAILY_GOAL);
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobMapper.toDto(job)).thenReturn(jobDto);
//
//        JobDto result = jobService.findById(JOB_ID);
//        assertThat(result).isEqualTo(jobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobMapper).toDto(job);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("updateJobStatus with valid ID and request updates the job status and returns updated JobDto")
//    public void updateJobStatus_ValidIdAndRequest_UpdatesJobStatusAndReturnsJobDto() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        Job updatedJob = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                Job.JobStatus.APPLIED, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto updatedJobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                Job.JobStatus.APPLIED, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        UpdateJobStatusRequestDto requestDto = new UpdateJobStatusRequestDto(Job.JobStatus.APPLIED);
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobRepository.save(job)).thenReturn(updatedJob);
//        when(jobMapper.toDto(updatedJob)).thenReturn(updatedJobDto);
//
//        JobDto result = jobService.updateJobStatus(JOB_ID, requestDto);
//
//        assertThat(result).isEqualTo(updatedJobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobRepository).save(job);
//        verify(jobMapper).toDto(updatedJob);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("updateCompanyName with valid ID and request updates company name and returns updated JobDto")
//    public void updateCompanyName_ValidIdAndRequest_UpdatesCompanyNameAndReturnsJobDto() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        Job updatedJob = createJob(JOB_ID, UPDATED_COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto updatedJobDto = createJobDto(JOB_ID, UPDATED_COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        UpdateCompanyNameRequestDto requestDto = new UpdateCompanyNameRequestDto(UPDATED_COMPANY_NAME);
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobRepository.save(job)).thenReturn(updatedJob);
//        when(jobMapper.toDto(updatedJob)).thenReturn(updatedJobDto);
//
//        JobDto result = jobService.updateCompanyName(JOB_ID, requestDto);
//
//        assertThat(result).isEqualTo(updatedJobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobRepository).save(job);
//        verify(jobMapper).toDto(updatedJob);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("updatePosition with valid ID and request updates position and returns updated JobDto")
//    public void updatePosition_ValidIdAndRequest_UpdatesPositionAndReturnsJobDto() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        Job updatedJob = createJob(JOB_ID, COMPANY_NAME, UPDATED_POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto updatedJobDto = createJobDto(JOB_ID, COMPANY_NAME, UPDATED_POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        UpdatePositionRequestDto requestDto = new UpdatePositionRequestDto(UPDATED_POSITION);
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobRepository.save(job)).thenReturn(updatedJob);
//        when(jobMapper.toDto(updatedJob)).thenReturn(updatedJobDto);
//
//        JobDto result = jobService.updatePosition(JOB_ID, requestDto);
//
//        assertThat(result).isEqualTo(updatedJobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobRepository).save(job);
//        verify(jobMapper).toDto(updatedJob);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("updateSalary with valid ID and request updates salary and returns updated JobDto")
//    public void updateSalary_ValidIdAndRequest_UpdatesSalaryAndReturnsJobDto() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        Job updatedJob = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, UPDATED_SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto updatedJobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, UPDATED_SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        UpdateSalaryRequestDto requestDto = new UpdateSalaryRequestDto("3000.0");
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobRepository.save(job)).thenReturn(updatedJob);
//        when(jobMapper.toDto(updatedJob)).thenReturn(updatedJobDto);
//
//        JobDto result = jobService.updateSalary(JOB_ID, requestDto);
//
//        assertThat(result).isEqualTo(updatedJobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobRepository).save(job);
//        verify(jobMapper).toDto(updatedJob);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("updateApplicationDate with valid ID and request updates application date and returns updated JobDto")
//    public void updateApplicationDate_ValidIdAndRequest_UpdatesApplicationDateAndReturnsJobDto() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        Job updatedJob = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, UPDATED_APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto updatedJobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, UPDATED_APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        UpdateApplicationDateRequestDto requestDto = new UpdateApplicationDateRequestDto(UPDATED_APPLICATION_DATE);
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobRepository.save(job)).thenReturn(updatedJob);
//        when(jobMapper.toDto(updatedJob)).thenReturn(updatedJobDto);
//
//        JobDto result = jobService.updateApplicationDate(JOB_ID, requestDto);
//
//        assertThat(result).isEqualTo(updatedJobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobRepository).save(job);
//        verify(jobMapper).toDto(updatedJob);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("updateJobType with valid ID and request updates job type and returns updated JobDto")
//    public void updateJobType_ValidIdAndRequest_UpdatesJobTypeAndReturnsJobDto() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        Job updatedJob = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, Job.JobType.REMOTE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto updatedJobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, Job.JobType.REMOTE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        UpdateJobTypeRequestDto requestDto = new UpdateJobTypeRequestDto(Job.JobType.REMOTE);
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobRepository.save(job)).thenReturn(updatedJob);
//        when(jobMapper.toDto(updatedJob)).thenReturn(updatedJobDto);
//
//        JobDto result = jobService.updateJobType(JOB_ID, requestDto);
//
//        assertThat(result).isEqualTo(updatedJobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobRepository).save(job);
//        verify(jobMapper).toDto(updatedJob);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("updateLink with valid ID and request updates link and returns updated JobDto")
//    public void updateLink_ValidIdAndRequest_UpdatesLinkAndReturnsJobDto() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        Job updatedJob = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, UPDATED_LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto updatedJobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, UPDATED_LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        UpdateLinkRequestDto requestDto = new UpdateLinkRequestDto(UPDATED_LINK);
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobRepository.save(job)).thenReturn(updatedJob);
//        when(jobMapper.toDto(updatedJob)).thenReturn(updatedJobDto);
//
//        JobDto result = jobService.updateLink(JOB_ID, requestDto);
//
//        assertThat(result).isEqualTo(updatedJobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobRepository).save(job);
//        verify(jobMapper).toDto(updatedJob);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("updateTags with valid ID and request adds new tags and returns updated JobDto")
//    public void updateTags_ValidIdAndRequest_AddsNewTagsAndReturnsJobDto() {
//        Set<String> initialTags = new HashSet<>(Set.of("Java", "Spring"));
//        Set<String> updatedTags = new HashSet<>(Set.of("Java", "Spring", "Hibernate"));
//
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, initialTags, NOTES, null);
//
//        Job updatedJob = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, updatedTags, NOTES, null);
//
//        JobDto updatedJobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, updatedTags, NOTES, null);
//
//        UpdateTagsRequestDto requestDto = new UpdateTagsRequestDto(new HashSet<>(Set.of("Hibernate")));
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(jobRepository.save(job)).thenReturn(updatedJob);
//        when(jobMapper.toDto(updatedJob)).thenReturn(updatedJobDto);
//
//        JobDto result = jobService.updateTags(JOB_ID, requestDto);
//
//        assertThat(result).isEqualTo(updatedJobDto);
//
//        verify(jobRepository).findById(JOB_ID);
//        verify(jobRepository).save(job);
//        verify(jobMapper).toDto(updatedJob);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("getOnlyAppliedStatus with valid request returns list of applied jobs")
//    public void getOnlyAppliedStatus_ValidRequest_ReturnsListOfAppliedJobs() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                Job.JobStatus.APPLIED, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto jobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                Job.JobStatus.APPLIED, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        when(jobRepository.findByJobStatus(Job.JobStatus.APPLIED)).thenReturn(List.of(job));
//        when(jobMapper.toDto(job)).thenReturn(jobDto);
//
//        List<JobDto> onlyAppliedStatus = jobService.getOnlyAppliedStatus();
//
//        assertThat(onlyAppliedStatus).hasSize(1);
//        assertThat(onlyAppliedStatus.get(0)).isEqualTo(jobDto);
//
//        verify(jobRepository).findByJobStatus(Job.JobStatus.APPLIED);
//        verify(jobMapper).toDto(job);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("getOnlyRejectedStatus with valid request returns list of rejected jobs")
//    public void getOnlyRejectedStatus_ValidRequest_ReturnsListOfRejectedJobs() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                Job.JobStatus.REJECTED, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto jobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                Job.JobStatus.REJECTED, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        when(jobRepository.findByJobStatus(Job.JobStatus.REJECTED)).thenReturn(List.of(job));
//        when(jobMapper.toDto(job)).thenReturn(jobDto);
//
//        List<JobDto> onlyRejectedStatus = jobService.getOnlyRejectedStatus();
//
//        assertThat(onlyRejectedStatus).hasSize(1);
//        assertThat(onlyRejectedStatus.get(0)).isEqualTo(jobDto);
//
//        verify(jobRepository).findByJobStatus(Job.JobStatus.REJECTED);
//        verify(jobMapper).toDto(job);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("getOnlyExpiredStatus with valid request returns list of expired jobs")
//    public void getOnlyExpiredStatus_ValidRequest_ReturnsListOfExpiredJobs() {
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                Job.JobStatus.EXPIRED, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto jobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                Job.JobStatus.EXPIRED, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        when(jobRepository.findByJobStatus(Job.JobStatus.EXPIRED)).thenReturn(List.of(job));
//        when(jobMapper.toDto(job)).thenReturn(jobDto);
//
//        List<JobDto> onlyExpiredStatus = jobService.getOnlyExpiredStatus();
//
//        assertThat(onlyExpiredStatus).hasSize(1);
//        assertThat(onlyExpiredStatus.get(0)).isEqualTo(jobDto);
//
//        verify(jobRepository).findByJobStatus(Job.JobStatus.EXPIRED);
//        verify(jobMapper).toDto(job);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("getOnlyExpiredStatus with valid request returns list of expired jobs")
//    public void addFavouriteJob_ValidJobId_AddsJobToUserFavouritesAndReturnsJobDto() {
//        Authentication authentication = mock(Authentication.class);
//        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto jobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        when(jobRepository.findById(JOB_ID)).thenReturn(Optional.of(job));
//        when(user.getFavourite()).thenReturn(List.of());
//        when(userRepository.save(user)).thenReturn(user);
//        when(jobMapper.toDto(job)).thenReturn(jobDto);
//
//        JobDto result = jobService.addFavouriteJob(JOB_ID, authentication);
//
//        assertThat(result).isEqualTo(jobDto);
//        verify(jobRepository).findById(JOB_ID);
//        verify(userRepository).save(user);
//        verify(jobMapper).toDto(job);
//        verifyNoMoreInteractions(jobRepository, userRepository, jobMapper);
//    }
//
//    @Test
//    @DisplayName("findByUserFavouriteJobs returns list of favourite JobDto objects")
//    public void findByUserFavouriteJobs_ReturnsListOfFavouriteJobDtos() {
//        Authentication authentication = mock(Authentication.class);
//        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
//
//        Job job = createJob(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        JobDto jobDto = createJobDto(JOB_ID, COMPANY_NAME, POSITION, LOCATION, SALARY, APPLICATION_DATE,
//                JOB_STATUS, JOB_TYPE, FEED_BACK_DATE, LINK, COMPANY_WEBSITE, CONTACT_EMAIL, KILOMETERS, TAGS, NOTES, null);
//
//        when(authentication.getPrincipal()).thenReturn(user);
//        when(jobRepository.findByUserFavouriteJobs(user.getId())).thenReturn(List.of(job));
//        when(jobMapper.toDto(job)).thenReturn(jobDto);
//
//        // When
//        List<JobDto> result = jobService.findByUserFavouriteJobs(authentication);
//
//        // Then
//        assertThat(result).hasSize(1);
//        assertThat(result).containsExactly(jobDto);
//        verify(jobRepository).findByUserFavouriteJobs(user.getId());
//        verify(jobMapper).toDto(job);
//        verifyNoMoreInteractions(jobRepository, jobMapper);
//    }
//
//    private Job createJob(Long id, String companyName, String position, String location, String salary,
//                          LocalDate application_date, Job.JobStatus jobStatus, Job.JobType jobType, LocalDate feedBackDate,
//                          String link, String companyWebsite, String contactEmail,
//                          Double kilometers, Set<String> tags, String notes, User user) {
//        Job job = new Job();
//        job.setId(id);
//        job.setCompanyName(companyName);
//        job.setPosition(position);
//        job.setLocation(location);
//        job.setSalary(salary);
//        job.setApplicationDate(application_date);
//        job.setJobStatus(jobStatus);
//        job.setJobType(jobType);
//        job.setFeedBackDate(feedBackDate);
//        job.setLink(link);
//        job.setCompanyWebsite(companyWebsite);
//        job.setContactEmail(contactEmail);
//        job.setKilometers(kilometers);
//        job.setTags(tags);
//        job.setNotes(notes);
//        job.setUser(user);
//        return job;
//    }
//
//    private User createUser(Long id, String firstName, String lastName, Integer dailyGoal) {
//        User user = new User();
//        user.setId(id);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setDailyGoal(dailyGoal);
//        return user;
//    }
//
//    private JobDto createJobDto(Long id, String companyName, String position, String location, String salary,
//                                LocalDate application_date, Job.JobStatus jobStatus, Job.JobType jobType, LocalDate feedBackDate,
//                                String link, String companyWebsite, String contactEmail,
//                                Double kilometers, Set<String> tags, String notes, Integer dailyGoal) {
//        JobDto jobDto = new JobDto(
//                id, companyName, position, location, salary, application_date,
//                feedBackDate, jobStatus, jobType, link, companyWebsite, contactEmail, kilometers, tags, notes, dailyGoal
//        );
//        return jobDto;
//    }
//}
