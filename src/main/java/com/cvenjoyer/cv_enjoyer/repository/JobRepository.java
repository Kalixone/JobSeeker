package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByJobStatus(Job.JobStatus jobStatus);
    List<Job> findByKilometersBetween(Double from, Double to);
    List<Job> findByUser(User user);
    Long countByUser(User user);
    Long countByUserAndJobStatus(User user, Job.JobStatus jobStatus);
    Long countByUserAndJobType(User user, Job.JobType jobType);
    Long countByUserAndApplicationDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
