package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.DevToDto;
import com.cvenjoyer.cv_enjoyer.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<DevToDto> getAllArticles(Authentication authentication) {
        return articleService.fetchAllArticles(authentication);
    }

    @GetMapping("byKeyword")
    @PreAuthorize("hasAuthority('USER')")
    public List<DevToDto> getArticlesByKeyword(@RequestParam String word) {
        return articleService.fetchArticlesByKeyWord(word);
    }

    @GetMapping("byTitle")
    @PreAuthorize("hasAuthority('USER')")
    public List<DevToDto> fetchArtcilesByTitle(@RequestParam String title) {
        return articleService.fetchArtcilesByTitle(title);
    }

    @GetMapping("byDescription")
    @PreAuthorize("hasAuthority('USER')")
    public List<DevToDto> fetchArtcilesByDescription(@RequestParam String description) {
        return articleService.fetchArtcilesByDescription(description);
    }
}
