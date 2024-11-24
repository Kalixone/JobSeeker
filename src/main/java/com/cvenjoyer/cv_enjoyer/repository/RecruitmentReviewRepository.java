package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.RecruitmentReview;
import com.cvenjoyer.cv_enjoyer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitmentReviewRepository extends JpaRepository<RecruitmentReview, Long> {
    List<RecruitmentReview> findByUser(User user);
    Optional<RecruitmentReview> findByIdAndUser(Long id, User user);
    List<RecruitmentReview> findByCompanyNameIgnoreCaseContaining(String companyName);
    List<RecruitmentReview> findByUserAndCompanyNameIgnoreCaseContaining(User user, String companyName);
    List<RecruitmentReview> findByRating(Double rating);
    List<RecruitmentReview> findByUserAndRating(User user, Double rating);
    List<RecruitmentReview> findByStages(Integer stage);
    List<RecruitmentReview> findByUserAndStages(User user, Integer stage);
}
