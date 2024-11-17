package com.cvenjoyer.cv_enjoyer.mapper;

import com.cvenjoyer.cv_enjoyer.dto.CreateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.dto.JobDto;
import com.cvenjoyer.cv_enjoyer.dto.UpdateJobRequestDto;
import com.cvenjoyer.cv_enjoyer.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JobMapper {
    Job toModel(CreateJobRequestDto createJobRequestDto);
    JobDto toDto(Job job);
    Job updateJob(UpdateJobRequestDto updateJobRequestDto, @MappingTarget Job job);
}
