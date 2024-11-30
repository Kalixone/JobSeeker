package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.DevToDto;
import com.cvenjoyer.cv_enjoyer.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Fetch all articles", description = "Returns a list of all articles available for the authenticated user")
    public List<DevToDto> getAllArticles(Authentication authentication) {
        return articleService.fetchAllArticles(authentication);
    }

    @GetMapping("byKeyword")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Fetch articles by keyword", description = "Returns articles that contain the specified keyword in their content")
    public List<DevToDto> getArticlesByKeyword(@RequestParam String word) {
        return articleService.fetchArticlesByKeyWord(word);
    }

    @GetMapping("byTitle")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Fetch articles by title", description = "Returns articles that match the specified title")
    public List<DevToDto> fetchArtcilesByTitle(@RequestParam String title) {
        return articleService.fetchArtcilesByTitle(title);
    }

    @GetMapping("byDescription")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Fetch articles by description", description = "Returns articles that match the specified description")
    public List<DevToDto> fetchArtcilesByDescription(@RequestParam String description) {
        return articleService.fetchArtcilesByDescription(description);
    }
}
