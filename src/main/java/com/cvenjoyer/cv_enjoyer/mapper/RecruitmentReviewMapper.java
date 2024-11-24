package com.cvenjoyer.cv_enjoyer.mapper;

import com.cvenjoyer.cv_enjoyer.dto.CreateRecruitmentReviewRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.RecruitmentReviewDto;
import com.cvenjoyer.cv_enjoyer.model.RecruitmentReview;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecruitmentReviewMapper {
    RecruitmentReview toModel(CreateRecruitmentReviewRequestDto createRecruitmentReviewRequestDto);
    RecruitmentReviewDto toDto(RecruitmentReview recruitmentReview);
}
