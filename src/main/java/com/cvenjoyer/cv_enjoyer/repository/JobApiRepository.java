package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.Job;
import com.cvenjoyer.cv_enjoyer.model.JobApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface JobApiRepository extends JpaRepository<JobApi, Long> {
    List<JobApi> findByCompanyNameIgnoreCaseContaining(String name);
    List<JobApi> findByPositionIgnoreCaseContaining(String position);
    @Query("SELECT j FROM JobApi j JOIN j.tags t WHERE t IN :tags")
    List<JobApi> findByTagsContaining(Set<String> tags);
    List<JobApi> findByJobType(Job.JobType jobType);
    List<JobApi> findByPublicationDateBetween(LocalDate startDate, LocalDate endDate);
    List<JobApi> findByCandidateRequiredLocationIgnoreCaseContaining(String location);
}
