package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.RemoteApiJobDto;
import org.springframework.security.core.Authentication;
import java.util.List;

public interface JobFetcher {
    List<RemoteApiJobDto> fetchAndSaveJobsFromRemotiveApi(Authentication authentication);
}
