package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.CreateRecruitmentReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.RecruitmentReviewDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.service.RecruitmentReviewService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create recruitment review", description = "Create a recruitment review for a specific company based on the user's experience.")
    public RecruitmentReviewDto createReview(@RequestBody CreateRecruitmentReviewRequestDto createRecruitmentReviewRequestDto, Authentication authentication) {
       return recruitmentReviewService.createReview(createRecruitmentReviewRequestDto, authentication);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get all recruitment reviews", description = "Fetch a list of all available recruitment reviews.")
    public List<RecruitmentReviewDto> getAllReviews() {
        return recruitmentReviewService.getAllReviews();
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get user's recruitment reviews", description = "Fetch a list of all recruitment reviews created by the authenticated user.")
    public List<RecruitmentReviewDto> getAllUsersReviews(Authentication authentication) {
        return recruitmentReviewService.getAllUsersReviews(authentication);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Delete recruitment review", description = "Delete a recruitment review based on its ID.")
    public void deleteReview(@PathVariable Long id, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        recruitmentReviewService.deleteReview(id, principal);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search reviews by company name", description = "Search recruitment reviews by company name.")
    public List<RecruitmentReviewDto> searchReviewsByCompanyName(@RequestParam String company) {
        return recruitmentReviewService.searchReviewsByCompanyName(company);
    }

    @GetMapping("/searchForUser")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search Reviews by Company for User", description = "Searches recruitment reviews left by a user for a specific company.")
    public List<RecruitmentReviewDto> searchUserReviewsByCompanyName(@RequestParam String company, Authentication authentication) {
        return recruitmentReviewService.searchUserReviewsByCompanyName(company, authentication);
    }

    @GetMapping("/searchByRating")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search Reviews by Rating", description = "Searches recruitment reviews based on a specified rating.")
    public List<RecruitmentReviewDto> findReviewsByRating(@RequestParam Double rating) {
        return recruitmentReviewService.findReviewsByRating(rating);
    }

    @GetMapping("/searchByRatingForUser")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search User Reviews by Rating", description = "Searches recruitment reviews left by a user based on a specified rating.")
    public List<RecruitmentReviewDto> findUserReviewsByRating(@RequestParam Double rating, Authentication authentication) {
        return recruitmentReviewService.findReviewsByUserAndRating(rating, authentication);
    }

    @GetMapping("/searchByStages")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search Reviews by Stage", description = "Searches recruitment reviews based on a specified recruitment stage.")
    public List<RecruitmentReviewDto> findReviewsByStages(@RequestParam Integer stage) {
        return recruitmentReviewService.findReviewsByStages(stage);
    }

    @GetMapping("/searchByStagesForUser")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search User Reviews by Stage", description = "Searches recruitment reviews left by a user based on a specified recruitment stage.")
    public List<RecruitmentReviewDto> findUserReviewsByStages(@RequestParam Integer stage, Authentication authentication) {
        return recruitmentReviewService.findReviewsByUserAndStages(stage, authentication);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Update Review", description = "Allows a user to update a specific recruitment review.")
    public RecruitmentReviewDto updateReview(@PathVariable Long id, @RequestBody UpdateReviewRequestDto updateReviewRequestDto, Authentication authentication) {
        return recruitmentReviewService.updateReview(id, updateReviewRequestDto, authentication);
    }
}
