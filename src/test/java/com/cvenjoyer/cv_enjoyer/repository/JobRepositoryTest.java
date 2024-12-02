package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobRepositoryTest {
    private static final Job.JobStatus JOB_STATUS = Job.JobStatus.APPLIED;
    @Autowired
    private JobRepository jobRepository;

    @Test
    @DisplayName("Verify findByJobStatus() returns jobs with the correct status")
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/jobs/add-jobs-to-jobs_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByJobStatus_ShouldReturnJobs_WhenJobStatusIsGiven() {
        List<Job> byJobStatus = jobRepository.findByJobStatus(JOB_STATUS);
        assertThat(byJobStatus).isNotEmpty();
        assertThat(byJobStatus.get(0).getJobStatus()).isEqualTo(Job.JobStatus.APPLIED);
    }

    @Test
    @DisplayName("Verify findByUser() returns jobs for the given user")
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/jobs/add-jobs-to-jobs_table.sql",
            "classpath:database/users/add-users-to-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/users/delete-users-from-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findByUser_ShouldReturnJobs_WhenUserIsGiven() {
        User user = new User();
        user.setId(1L);
        List<Job> jobs = jobRepository.findByUser(user);
        assertThat(jobs).isNotEmpty();
        assertThat(jobs.get(0).getUser()).isEqualTo(user);
    }

    @Test
    @DisplayName("Verify countByUser() returns the correct count of jobs for the user")
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/jobs/add-jobs-to-jobs_table.sql",
            "classpath:database/users/add-users-to-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/users/delete-users-from-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void countByUser_ShouldReturnCorrectJobCount() {
        User user = new User();
        user.setId(1L);
        Long count = jobRepository.countByUser(user);
        assertThat(count).isGreaterThan(0);
    }

    @Test
    @DisplayName("Verify countByUserAndJobStatus() returns the correct job count for user and status")
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/jobs/add-jobs-to-jobs_table.sql",
            "classpath:database/users/add-users-to-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/users/delete-users-from-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void countByUserAndJobStatus_ShouldReturnCorrectJobCount() {
        User user = new User();
        user.setId(1L);
        Long count = jobRepository.countByUserAndJobStatus(user, Job.JobStatus.APPLIED);
        assertThat(count).isGreaterThan(0);
    }

    @Test
    @DisplayName("Verify countByUserAndJobType() returns the correct job count for user and job type")
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/jobs/add-jobs-to-jobs_table.sql",
            "classpath:database/users/add-users-to-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/users/delete-users-from-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void countByUserAndJobType_ShouldReturnCorrectJobCount() {
        User user = new User();
        user.setId(1L);
        Long count = jobRepository.countByUserAndJobType(user, Job.JobType.FULL_TIME);
        assertThat(count).isGreaterThan(0);
    }

    @Test
    @DisplayName("Verify countByUserAndApplicationDateBetween() returns correct count for given date range")
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/jobs/add-jobs-to-jobs_table.sql",
            "classpath:database/users/add-users-to-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/jobs/delete-jobs-from-jobs_table.sql",
            "classpath:database/users/delete-users-from-users_table.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void countByUserAndApplicationDateBetween_ShouldReturnCorrectJobCount() {
        User user = new User();
        user.setId(1L);
        Long count = jobRepository.countByUserAndApplicationDateBetween(user, LocalDate.of(2023, 1, 1), LocalDate.of(2023, 12, 31));
        assertThat(count).isGreaterThan(0);
    }
}
