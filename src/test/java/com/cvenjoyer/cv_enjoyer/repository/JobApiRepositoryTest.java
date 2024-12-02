package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.JobApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobApiRepositoryTest {
    @Autowired
    private JobApiRepository jobApiRepository;

    @Test
    @DisplayName("Verify findByCompanyNameIgnoreCaseContaining() returns jobs containing the company name")
    @Sql(scripts = "classpath:database/jobapi/add-jobapi-jobs.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/jobapi/delete-jobapi-jobs.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByCompanyNameIgnoreCaseContaining_ShouldReturnJobs_WhenCompanyNameIsGiven() {
        List<JobApi> jobs = jobApiRepository.findByCompanyNameIgnoreCaseContaining("Tech Corp");
        assertThat(jobs).isNotEmpty();
        assertThat(jobs.get(0).getCompanyName()).containsIgnoringCase("Tech Corp");
    }

    @Test
    @DisplayName("Verify findByPositionIgnoreCaseContaining() returns jobs containing the position name")
    @Sql(scripts = "classpath:database/jobapi/add-jobapi-jobs.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/jobapi/delete-jobapi-jobs.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByPositionIgnoreCaseContaining_ShouldReturnJobs_WhenPositionIsGiven() {
        List<JobApi> jobs = jobApiRepository.findByPositionIgnoreCaseContaining("Software Engineer");
        assertThat(jobs).isNotEmpty();
        assertThat(jobs.get(0).getPosition()).containsIgnoringCase("Software Engineer");
    }

    @Test
    @DisplayName("Verify findByTagsContaining() returns jobs containing the specified tags")
    @Sql(scripts = "classpath:database/jobapi/add-jobapi-jobs.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/jobapi/delete-jobapi-jobs.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByTagsContaining_ShouldReturnJobs_WhenTagsAreGiven() {
        Set<String> tags = Set.of("Java", "Backend");
        List<JobApi> jobs = jobApiRepository.findByTagsContaining(tags);
        assertThat(jobs).isNotEmpty();
        assertThat(jobs.get(0).getTags()).containsExactlyInAnyOrderElementsOf(tags);
    }

    @Test
    @DisplayName("Verify findByPublicationDateBetween() returns jobs published within the given date range")
    @Sql(scripts = "classpath:database/jobapi/add-jobapi-jobs.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:database/jobapi/delete-jobapi-jobs.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByPublicationDateBetween_ShouldReturnJobs_WhenDateRangeIsGiven() {
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        List<JobApi> jobs = jobApiRepository.findByPublicationDateBetween(startDate, endDate);
        assertThat(jobs).isNotEmpty();
        assertThat(jobs.get(0).getPublicationDate()).isBetween(startDate, endDate);
    }
}
