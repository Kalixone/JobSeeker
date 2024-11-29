package com.cvenjoyer.cv_enjoyer.mapper;

import com.cvenjoyer.cv_enjoyer.dto.DevToDto;
import com.cvenjoyer.cv_enjoyer.model.DevTo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DevToMapper {
    DevToDto toDto(DevTo devTo);
}
