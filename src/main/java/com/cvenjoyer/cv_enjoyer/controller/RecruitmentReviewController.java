package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.CreateRecruitmentReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.RecruitmentReviewDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.service.RecruitmentReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class RecruitmentReviewController {
    private final RecruitmentReviewService recruitmentReviewService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public RecruitmentReviewDto createReview(@RequestBody CreateRecruitmentReviewRequestDto createRecruitmentReviewRequestDto, Authentication authentication) {
       return recruitmentReviewService.createReview(createRecruitmentReviewRequestDto, authentication);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public List<RecruitmentReviewDto> getAllReviews() {
        return recruitmentReviewService.getAllReviews();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER')")
    public List<RecruitmentReviewDto> getAllUsersReviews(Authentication authentication) {
        return recruitmentReviewService.getAllUsersReviews(authentication);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public void deleteReview(@PathVariable Long id, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        recruitmentReviewService.deleteReview(id, principal);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('USER')")
    public List<RecruitmentReviewDto> searchReviewsByCompanyName(@RequestParam String company) {
        return recruitmentReviewService.searchReviewsByCompanyName(company);
    }

    @GetMapping("/searchForUser")
    @PreAuthorize("hasAuthority('USER')")
    public List<RecruitmentReviewDto> searchUserReviewsByCompanyName(@RequestParam String company, Authentication authentication) {
        return recruitmentReviewService.searchUserReviewsByCompanyName(company, authentication);
    }

    @GetMapping("/searchByRating")
    @PreAuthorize("hasAuthority('USER')")
    public List<RecruitmentReviewDto> findReviewsByRating(@RequestParam Double rating) {
        return recruitmentReviewService.findReviewsByRating(rating);
    }

    @GetMapping("/searchByRatingForUser")
    @PreAuthorize("hasAuthority('USER')")
    public List<RecruitmentReviewDto> findUserReviewsByRating(@RequestParam Double rating, Authentication authentication) {
        return recruitmentReviewService.findReviewsByUserAndRating(rating, authentication);
    }

    @GetMapping("/searchByStages")
    @PreAuthorize("hasAuthority('USER')")
    public List<RecruitmentReviewDto> findReviewsByStages(@RequestParam Integer stage) {
        return recruitmentReviewService.findReviewsByStages(stage);
    }

    @GetMapping("/searchByStagesForUser")
    @PreAuthorize("hasAuthority('USER')")
    public List<RecruitmentReviewDto> findUserReviewsByStages(@RequestParam Integer stage, Authentication authentication) {
        return recruitmentReviewService.findReviewsByUserAndStages(stage, authentication);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public RecruitmentReviewDto updateReview(@PathVariable Long id, @RequestBody UpdateReviewRequestDto updateReviewRequestDto, Authentication authentication) {
        return recruitmentReviewService.updateReview(id, updateReviewRequestDto, authentication);
    }
}
