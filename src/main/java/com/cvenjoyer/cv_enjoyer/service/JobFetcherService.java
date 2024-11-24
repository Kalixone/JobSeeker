package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.RemoteApiJobDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobFetcherService {
    List<RemoteApiJobDto> fetchAndSaveJobsFromRemotiveApi(Authentication authentication);
}
