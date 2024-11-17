package com.cvenjoyer.cv_enjoyer.mapper;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.model.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {
    Job toModel(CreateJobRequestDto createJobRequestDto);
    JobDto toDto(Job job);
}
