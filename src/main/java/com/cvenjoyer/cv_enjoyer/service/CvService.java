package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.CreateCvTemplateRequestDto;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public interface CvService {
    File generateCvFile(CreateCvTemplateRequestDto requestDto);
}
