package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.DevToDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ArticleService {
    List<DevToDto> fetchAllArticles(Authentication authentication);
    List<DevToDto> fetchArticlesByKeyWord(String word);
    List<DevToDto> fetchArtcilesByTitle(String title);
    List<DevToDto> fetchArtcilesByDescription(String description);
}
