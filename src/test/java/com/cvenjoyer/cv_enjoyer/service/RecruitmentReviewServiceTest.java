package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.CreateRecruitmentReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.RecruitmentReviewDto;
import com.cvenjoyer.cv_enjoyer.mapper.RecruitmentReviewMapper;
import com.cvenjoyer.cv_enjoyer.model.RecruitmentReview;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.RecruitmentReviewRepository;
import com.cvenjoyer.cv_enjoyer.service.impl.RecruitmentReviewServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecruitmentReviewServiceTest {
    private static final Long REVIEW_ID = 1L;
    private static final String COMPANY_NAME = "allegro";
    private static final String POSITION = "Junior Java Developer";
    private static final Integer STAGES = 3;
    private static final String REVIEW = "unprofessional";
    private static final Double RATING = 3.0;
    private static final LocalDateTime REVIEW_DATE = LocalDateTime.now();
    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "Piotr";
    private static final String LAST_NAME = "Kendzior";
    private static final Integer DAILY_GOAL = 3;

    @Mock
    private RecruitmentReviewRepository recruitmentReviewRepository;
    @Mock
    private RecruitmentReviewMapper recruitmentReviewMapper;
    @InjectMocks
    private RecruitmentReviewServiceImpl recruitmentReviewService;

    @Test
    @DisplayName("createReview returns the created RecruitmentReviewDto")
    public void createReview_ReturnsCreatedRecruitmentReviewDto()  {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);
        RecruitmentReviewDto recruitmentReviewDto = createReviewDto(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE);
        CreateRecruitmentReviewRequestDto createRecruitmentReviewRequestDto = new CreateRecruitmentReviewRequestDto(COMPANY_NAME, POSITION, STAGES, REVIEW, RATING);

        when(authentication.getPrincipal()).thenReturn(user);
        when(recruitmentReviewRepository.save(any(RecruitmentReview.class))).thenReturn(review);
        when(recruitmentReviewMapper.toDto(review)).thenReturn(recruitmentReviewDto);

        RecruitmentReviewDto result = recruitmentReviewService.createReview(createRecruitmentReviewRequestDto, authentication);

        assertThat(result).isEqualTo(recruitmentReviewDto);
        verify(recruitmentReviewRepository).save(any(RecruitmentReview.class));
        verify(recruitmentReviewMapper).toDto(any(RecruitmentReview.class));
        verifyNoMoreInteractions(recruitmentReviewRepository, recruitmentReviewMapper);;
    }

    @Test
    @DisplayName("getAllUsersReviews returns a list of RecruitmentReviewDto for the authenticated user")
    public void getAllUsersReviews_ReturnsListOfRecruitmentReviewDtoForAuthenticatedUser() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);
        RecruitmentReviewDto recruitmentReviewDto = createReviewDto(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE);

        when(authentication.getPrincipal()).thenReturn(user);
        when(recruitmentReviewRepository.findByUser(user)).thenReturn(List.of(review));
        when(recruitmentReviewMapper.toDto(review)).thenReturn(recruitmentReviewDto);

        List<RecruitmentReviewDto> allUsersReviews = recruitmentReviewService.getAllUsersReviews(authentication);

        assertThat(allUsersReviews).hasSize(1);
        assertThat(allUsersReviews.get(0)).isEqualTo(recruitmentReviewDto);
        verify(recruitmentReviewRepository).findByUser(user);
        verify(recruitmentReviewMapper).toDto(any(RecruitmentReview.class));
        verifyNoMoreInteractions(recruitmentReviewRepository, recruitmentReviewMapper);
    }

    @Test
    @DisplayName("getAllReviews returns a list of RecruitmentReviewDto from the recruitmentReviewRepository")
    public void getAllReviews_ReturnsListOfRecruitmentReviewDtoFromRepository() {
        // Given
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);
        RecruitmentReviewDto recruitmentReviewDto = createReviewDto(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE);

        when(recruitmentReviewRepository.findAll()).thenReturn(List.of(review));
        when(recruitmentReviewMapper.toDto(review)).thenReturn(recruitmentReviewDto);

        List<RecruitmentReviewDto> allReviews = recruitmentReviewService.getAllReviews();

        assertThat(allReviews).hasSize(1);
        assertThat(allReviews.get(0)).isEqualTo(recruitmentReviewDto);
        verify(recruitmentReviewRepository).findAll();
        verify(recruitmentReviewMapper).toDto(any(RecruitmentReview.class));
        verifyNoMoreInteractions(recruitmentReviewRepository, recruitmentReviewMapper);
    }

    @Test
    @DisplayName("deleteReview removes the specified review from the repository")
    public void deleteReview_RemovesTheSpecifiedReviewFromTheRepository() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);

        when(recruitmentReviewRepository.findByIdAndUser(REVIEW_ID, user)).thenReturn(Optional.of(review));

        recruitmentReviewService.deleteReview(REVIEW_ID, user);

        verify(recruitmentReviewRepository).delete(review);
        verifyNoMoreInteractions(recruitmentReviewRepository);
    }

    @Test
    @DisplayName("searchReviewsByCompanyName returns a list of RecruitmentReviewDto filtered by company name")
    public void searchReviewsByCompanyName_ReturnsListOfRecruitmentReviewDtoFilteredByCompanyName() {
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);
        RecruitmentReviewDto recruitmentReviewDto = createReviewDto(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE);

        when(recruitmentReviewRepository.findByCompanyNameIgnoreCaseContaining(COMPANY_NAME)).thenReturn(List.of(review));
        when(recruitmentReviewMapper.toDto(review)).thenReturn(recruitmentReviewDto);

        List<RecruitmentReviewDto> recruitmentReviewDtos = recruitmentReviewService.searchReviewsByCompanyName(COMPANY_NAME);

        assertThat(recruitmentReviewDtos).hasSize(1);
        assertThat(recruitmentReviewDtos.get(0)).isEqualTo(recruitmentReviewDto);
        verify(recruitmentReviewRepository).findByCompanyNameIgnoreCaseContaining(COMPANY_NAME);
        verify(recruitmentReviewMapper).toDto(any(RecruitmentReview.class));
        verifyNoMoreInteractions(recruitmentReviewRepository, recruitmentReviewMapper);
    }

    @Test
    @DisplayName("searchUsersReviewsByCompanyName returns a list of RecruitmentReviewDto for the authenticated user filtered by company name")
    public void searchUsersReviewsByCompanyName_ReturnsListOfRecruitmentReviewDtoForAuthenticatedUserFilteredByCompanyName() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);
        RecruitmentReviewDto recruitmentReviewDto = createReviewDto(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE);

        when(authentication.getPrincipal()).thenReturn(user);
        when(recruitmentReviewRepository.findByUserAndCompanyNameIgnoreCaseContaining(user, COMPANY_NAME)).thenReturn(List.of(review));
        when(recruitmentReviewMapper.toDto(review)).thenReturn(recruitmentReviewDto);

        List<RecruitmentReviewDto> recruitmentReviewDtos = recruitmentReviewService.searchUserReviewsByCompanyName(COMPANY_NAME, authentication);

        assertThat(recruitmentReviewDtos).hasSize(1);
        assertThat(recruitmentReviewDtos.get(0)).isEqualTo(recruitmentReviewDto);
        verify(recruitmentReviewRepository).findByUserAndCompanyNameIgnoreCaseContaining(user, COMPANY_NAME);
        verify(recruitmentReviewMapper).toDto(any(RecruitmentReview.class));
        verifyNoMoreInteractions(recruitmentReviewRepository, recruitmentReviewMapper);
    }

    @Test
    @DisplayName("findReviewsByRating returns a list of RecruitmentReviewDto filtered by rating")
    public void findReviewsByRating_ReturnsListOfRecruitmentReviewDtoFilteredByRating() {
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);
        RecruitmentReviewDto recruitmentReviewDto = createReviewDto(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE);

        when(recruitmentReviewRepository.findByRating(RATING)).thenReturn(List.of(review));
        when(recruitmentReviewMapper.toDto(review)).thenReturn(recruitmentReviewDto);

        List<RecruitmentReviewDto> result = recruitmentReviewService.findReviewsByRating(RATING);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(recruitmentReviewDto);
        verify(recruitmentReviewRepository).findByRating(RATING);
        verify(recruitmentReviewMapper).toDto(any(RecruitmentReview.class));
        verifyNoMoreInteractions(recruitmentReviewRepository, recruitmentReviewMapper);
    }

    @Test
    @DisplayName("findUsersReviewsByRating returns a list of RecruitmentReviewDto for the authenticated user filtered by rating")
    public void findUsersReviewsByRating_ReturnsListOfRecruitmentReviewDtoForAuthenticatedUserFilteredByRating() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);
        RecruitmentReviewDto recruitmentReviewDto = createReviewDto(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE);

        when(recruitmentReviewRepository.findByUserAndRating(user, RATING)).thenReturn(List.of(review));
        when(recruitmentReviewMapper.toDto(review)).thenReturn(recruitmentReviewDto);

        List<RecruitmentReviewDto> result = recruitmentReviewService.findReviewsByUserAndRating(RATING, authentication);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(recruitmentReviewDto);

        verify(recruitmentReviewRepository).findByUserAndRating(user, RATING);
        verify(recruitmentReviewMapper).toDto(any(RecruitmentReview.class));
        verifyNoMoreInteractions(recruitmentReviewRepository, recruitmentReviewMapper);
    }

    @Test
    @DisplayName("findReviewsByStages returns a list of RecruitmentReviewDto filtered by stage")
    public void findReviewsByStages_ReturnsListOfRecruitmentReviewDtoFilteredByStages() {
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);
        RecruitmentReviewDto recruitmentReviewDto = createReviewDto(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE);

        when(recruitmentReviewRepository.findByStages(STAGES)).thenReturn(List.of(review));
        when(recruitmentReviewMapper.toDto(review)).thenReturn(recruitmentReviewDto);

        List<RecruitmentReviewDto> result = recruitmentReviewService.findReviewsByStages(STAGES);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(recruitmentReviewDto);
        verify(recruitmentReviewRepository).findByStages(STAGES);
        verify(recruitmentReviewMapper).toDto(any(RecruitmentReview.class));
        verifyNoMoreInteractions(recruitmentReviewRepository, recruitmentReviewMapper);
    }

    @Test
    @DisplayName("findUsersReviewsByStages returns a list of RecruitmentReviewDto for the authenticated user filtered by stage")
    public void findUsersReviewsByStages_ReturnsListOfRecruitmentReviewDtoForAuthenticatedUserFilteredByStages() {
        Authentication authentication = mock(Authentication.class);
        User user = createUser(USER_ID, FIRST_NAME, LAST_NAME, DAILY_GOAL);
        RecruitmentReview review = createReview(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE, user);
        RecruitmentReviewDto recruitmentReviewDto = createReviewDto(REVIEW_ID, COMPANY_NAME, POSITION, STAGES, REVIEW, RATING, REVIEW_DATE);

        when(authentication.getPrincipal()).thenReturn(user);
        when(recruitmentReviewRepository.findByUserAndStages(user, STAGES)).thenReturn(List.of(review));
        when(recruitmentReviewMapper.toDto(review)).thenReturn(recruitmentReviewDto);

        List<RecruitmentReviewDto> result = recruitmentReviewService.findReviewsByUserAndStages(STAGES, authentication);

        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(recruitmentReviewDto);

        verify(recruitmentReviewRepository).findByUserAndStages(user, STAGES);
        verify(recruitmentReviewMapper).toDto(any(RecruitmentReview.class));
        verifyNoMoreInteractions(recruitmentReviewRepository, recruitmentReviewMapper);
    }

    private User createUser(Long id, String firstName, String lastName, Integer dailyGoal) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setDailyGoal(dailyGoal);
        return user;
    }

    private RecruitmentReview createReview(Long id, String companyName, String position, Integer stages, String review, Double rating, LocalDateTime reviewDate, User user) {
        RecruitmentReview recruitmentReview = new RecruitmentReview();
        recruitmentReview.setId(id);
        recruitmentReview.setCompanyName(companyName);
        recruitmentReview.setPosition(position);
        recruitmentReview.setStages(stages);
        recruitmentReview.setReview(review);
        recruitmentReview.setRating(rating);
        recruitmentReview.setReviewDate(reviewDate);
        recruitmentReview.setUser(user);
        return recruitmentReview;
    }

    private RecruitmentReviewDto createReviewDto(Long id, String companyName, String position, Integer stages, String review, Double rating, LocalDateTime reviewDate) {
       return new RecruitmentReviewDto(id, companyName, position, stages, review, rating, reviewDate);
    }
}
