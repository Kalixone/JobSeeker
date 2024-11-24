package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.CreateRecruitmentReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.RecruitmentReviewDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RecruitmentReviewService {
    RecruitmentReviewDto createReview(CreateRecruitmentReviewRequestDto createRecruitmentReviewRequestDto, Authentication authentication);
    List<RecruitmentReviewDto> getAllUsersReviews(Authentication authentication);
    List<RecruitmentReviewDto> getAllReviews();
    void deleteReview(Long reviewId, User user);
    List<RecruitmentReviewDto> searchReviewsByCompanyName(String companyName);
    List<RecruitmentReviewDto> searchUserReviewsByCompanyName(String companyName, Authentication authentication);
    List<RecruitmentReviewDto> findReviewsByRating(Double rating);
    List<RecruitmentReviewDto> findReviewsByUserAndRating(Double rating, Authentication authentication);
    List<RecruitmentReviewDto> findReviewsByStages(Integer stage);
    List<RecruitmentReviewDto> findReviewsByUserAndStages(Integer stage, Authentication authentication);
    RecruitmentReviewDto updateReview(Long id, UpdateReviewRequestDto updateReviewRequestDto, Authentication authentication);
}
