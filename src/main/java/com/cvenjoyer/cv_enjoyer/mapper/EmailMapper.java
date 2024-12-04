package com.cvenjoyer.cv_enjoyer.mapper;

import com.cvenjoyer.cv_enjoyer.dto.EmailDto;
import com.cvenjoyer.cv_enjoyer.model.Email;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    EmailDto toDto(Email email);
}
