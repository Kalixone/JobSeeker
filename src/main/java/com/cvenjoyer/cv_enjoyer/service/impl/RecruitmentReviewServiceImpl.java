package com.cvenjoyer.cv_enjoyer.service.impl;


import com.cvenjoyer.cv_enjoyer.dto.CreateRecruitmentReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.RecruitmentReviewDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.exceptions.EntityNotFoundException;
import com.cvenjoyer.cv_enjoyer.mapper.RecruitmentReviewMapper;
import com.cvenjoyer.cv_enjoyer.model.RecruitmentReview;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.RecruitmentReviewRepository;
import com.cvenjoyer.cv_enjoyer.service.RecruitmentReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RecruitmentReviewServiceImpl implements RecruitmentReviewService {
    private final RecruitmentReviewRepository recruitmentReviewRepository;
    private final RecruitmentReviewMapper recruitmentReviewMapper;

    @Override
    public RecruitmentReviewDto createReview(CreateRecruitmentReviewRequestDto createRecruitmentReviewRequestDto, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();

        RecruitmentReview model = recruitmentReviewMapper.toModel(createRecruitmentReviewRequestDto);

        model.setUser(principal);

        if (model.getReviewDate() == null) {
            model.setReviewDate(LocalDateTime.now());
        }

        if (model.getRating() != null && model.getRating() >= 0 && model.getRating() <= 5) {
            recruitmentReviewRepository.save(model);
        }

        return recruitmentReviewMapper.toDto(model);
    }

    @Override
    public List<RecruitmentReviewDto> getAllUsersReviews(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return recruitmentReviewRepository.findByUser(principal).stream().map(recruitmentReviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentReviewDto> getAllReviews() {
        return recruitmentReviewRepository.findAll().stream().map(recruitmentReviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteReview(Long reviewId, User user) {
        Optional<RecruitmentReview> byIdAndUser = recruitmentReviewRepository.findByIdAndUser(reviewId, user);
        if (byIdAndUser.isPresent()) {
            recruitmentReviewRepository.delete(byIdAndUser.get());
        } else {
            throw new AccessDeniedException("You do not have permission to delete this review");
        }
    }

    @Override
    public List<RecruitmentReviewDto> searchReviewsByCompanyName(String companyName) {
        return recruitmentReviewRepository.findByCompanyNameIgnoreCaseContaining(companyName)
                .stream()
                .map(recruitmentReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentReviewDto> searchUserReviewsByCompanyName(String companyName, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return recruitmentReviewRepository.findByUserAndCompanyNameIgnoreCaseContaining(principal, companyName)
                .stream()
                .map(recruitmentReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentReviewDto> findReviewsByRating(Double rating) {
        return recruitmentReviewRepository.findByRating(rating).stream().filter(r -> r.getRating() <= 5 && r.getRating() > 0).map(recruitmentReviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentReviewDto> findReviewsByUserAndRating(Double rating, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return recruitmentReviewRepository.findByUserAndRating(principal, rating)
                .stream()
                .map(recruitmentReviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentReviewDto> findReviewsByStages(Integer stage) {
       return recruitmentReviewRepository.findByStages(stage).stream().map(recruitmentReviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<RecruitmentReviewDto> findReviewsByUserAndStages(Integer stage, Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return recruitmentReviewRepository.findByUserAndStages(principal, stage).stream().map(recruitmentReviewMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public RecruitmentReviewDto updateReview(Long id, UpdateReviewRequestDto updateReviewRequestDto, Authentication authentication) {
        // Wyszukaj recenzję po ID
        RecruitmentReview recruitmentReview = recruitmentReviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found by ID: " + id));

        // Pobierz zalogowanego użytkownika
        User principal = (User) authentication.getPrincipal();

        // Sprawdź, czy zalogowany użytkownik jest właścicielem recenzji
        if (!recruitmentReview.getUser().getId().equals(principal.getId())) {
            throw new AccessDeniedException("You do not have permission to update this review");
        }

        // Zaktualizuj recenzję
        recruitmentReview.setReview(updateReviewRequestDto.review());
        recruitmentReviewRepository.save(recruitmentReview);

        // Zwróć zaktualizowaną recenzję
        return recruitmentReviewMapper.toDto(recruitmentReview);
    }
}
