package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.RecruitmentReview;
import com.cvenjoyer.cv_enjoyer.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RecruitmentReviewRepositoryTest {
    @Autowired
    private RecruitmentReviewRepository recruitmentReviewRepository;

    @Test
    @DisplayName("Verify findByUser() returns reviews for the given user")
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/recruitment_reviews/add-recruitment-reviews-to-recruitment-reviews-table.sql",
            "classpath:database/users/add-users.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/users/delete-users.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByUser_ShouldReturnReviews_WhenUserIsGiven() {
        User user = new User();
        user.setId(1L);
        List<RecruitmentReview> reviews = recruitmentReviewRepository.findByUser(user);
        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).getUser()).isEqualTo(user);
    }

    @Test
    @DisplayName("Verify findByIdAndUser() returns review for given id and user")
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/recruitment_reviews/add-recruitment-reviews-to-recruitment-reviews-table.sql",
            "classpath:database/users/add-users.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/users/delete-users.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByIdAndUser_ShouldReturnReview_WhenIdAndUserAreGiven() {
        User user = new User();
        user.setId(1L);
        Optional<RecruitmentReview> review = recruitmentReviewRepository.findByIdAndUser(1L, user);
        assertThat(review).isPresent();
        assertThat(review.get().getUser()).isEqualTo(user);
    }

    @Test
    @DisplayName("Verify findByCompanyNameIgnoreCaseContaining() returns reviews containing the company name")
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/recruitment_reviews/add-recruitment-reviews-to-recruitment-reviews-table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByCompanyNameIgnoreCaseContaining_ShouldReturnReviews_WhenCompanyNameIsGiven() {
        List<RecruitmentReview> reviews = recruitmentReviewRepository.findByCompanyNameIgnoreCaseContaining("Tech Corp");
        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).getCompanyName()).containsIgnoringCase("Tech Corp");
    }

    @Test
    @DisplayName("Verify findByUserAndCompanyNameIgnoreCaseContaining() returns reviews for user and company name")
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/recruitment_reviews/add-recruitment-reviews-to-recruitment-reviews-table.sql",
            "classpath:database/users/add-users.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/users/delete-users.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByUserAndCompanyNameIgnoreCaseContaining_ShouldReturnReviews_WhenUserAndCompanyNameAreGiven() {
        User user = new User();
        user.setId(1L);
        List<RecruitmentReview> reviews = recruitmentReviewRepository.findByUserAndCompanyNameIgnoreCaseContaining(user, "Tech");
        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).getUser()).isEqualTo(user);
        assertThat(reviews.get(0).getCompanyName()).containsIgnoringCase("Tech");
    }

    @Test
    @DisplayName("Verify findByRating() returns reviews with the given rating")
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/recruitment_reviews/add-recruitment-reviews-to-recruitment-reviews-table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByRating_ShouldReturnReviews_WhenRatingIsGiven() {
        List<RecruitmentReview> reviews = recruitmentReviewRepository.findByRating(4.5);
        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).getRating()).isEqualTo(4.5);
    }

    @Test
    @DisplayName("Verify findByUserAndRating() returns reviews for user with the given rating")
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/recruitment-reviews/add-recruitment-reviews-to-recruitment-reviews-table.sql",
            "classpath:database/users/add-users.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/recruitment_reviews/delete-recruitment-reviews-from-recruitment-reviews-table.sql",
            "classpath:database/users/delete-users.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByUserAndRating_ShouldReturnReviews_WhenUserAndRatingAreGiven() {
        User user = new User();
        user.setId(1L);
        List<RecruitmentReview> reviews = recruitmentReviewRepository.findByUserAndRating(user, 4.5);
        assertThat(reviews).isNotEmpty();
        assertThat(reviews.get(0).getUser()).isEqualTo(user);
        assertThat(reviews.get(0).getRating()).isEqualTo(4.5);
    }
}
