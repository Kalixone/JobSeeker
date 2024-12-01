package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.DevToDto;
import com.cvenjoyer.cv_enjoyer.mapper.DevToMapper;
import com.cvenjoyer.cv_enjoyer.model.DevTo;
import com.cvenjoyer.cv_enjoyer.model.DevToStructure;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.DevToRepository;
import com.cvenjoyer.cv_enjoyer.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;
import java.util.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {
    private static final String TITLE = "Java Article";
    private static final String DESCRIPTION = "Java programming tutorial";
    private static final String DATE = "2024-12-01";
    private static final String URL = "https://example.com";
    private static final String TAG = "java, programming";
    private static final String KEYWORD = "java";

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private DevToRepository devToRepository;

    @Mock
    private DevToMapper devToMapper;

    @Test
    @DisplayName("Fetch all articles for given tags and return mapped articles")
    void fetchAllArticles_ReturnsMappedArticlesForGivenTags() {
        Set<String> programmingLanguages = new HashSet<>(Arrays.asList("java", "python"));
        Set<String> frameworks = new HashSet<>(Collections.singletonList("spring"));
        User principal = mock(User.class);
        when(principal.getProgrammingLanguages()).thenReturn(programmingLanguages);
        when(principal.getFrameworks()).thenReturn(frameworks);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(principal);

        DevToStructure devToStructure = createDevToStructure(TITLE, DESCRIPTION, DATE, URL, TAG);
        List<DevToStructure> responseList = Collections.singletonList(devToStructure);

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<DevToStructure>> responseEntity = ResponseEntity.ok(responseList);

        when(restTemplate.exchange(any(String.class), any(), eq(entity), any(Class.class)))
                .thenReturn(responseEntity);

        DevToDto devToDto = createDevToDto(1L, TITLE, DESCRIPTION, DATE, URL, TAG);
        when(devToMapper.toDto(any(DevTo.class))).thenReturn(devToDto);

        List<DevToDto> result = articleService.fetchAllArticles(authentication);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(TITLE, result.get(0).title());
        verify(devToRepository, times(1)).saveAll(any());
        verify(devToMapper, times(1)).toDto(any());
    }

    @Test
    @DisplayName("Fetch articles by keyword and return mapped articles")
    void fetchArticlesByKeyword_ReturnsArticlesMappedToDto() {
        DevToStructure devToStructure = createDevToStructure(TITLE, DESCRIPTION, DATE, URL, TAG);

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<DevToStructure>> responseEntity = ResponseEntity.ok(Collections.singletonList(devToStructure));

        when(restTemplate.exchange(any(String.class), any(), eq(entity), any(Class.class)))
                .thenReturn(responseEntity);

        DevToDto devToDto = createDevToDto(1L, TITLE, DESCRIPTION, DATE, URL, TAG);
        when(devToMapper.toDto(any(DevTo.class))).thenReturn(devToDto);

        List<DevToDto> result = articleService.fetchArticlesByKeyWord(KEYWORD);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(TITLE, result.get(0).title());
        verify(devToRepository, times(1)).saveAll(any());
    }

    @Test
    @DisplayName("Fetch articles by title and return filtered articles mapped to DTO")
    void fetchArticlesByTitle_ReturnsFilteredArticlesMappedToDto(){
        String title = TITLE;
        DevToStructure devToStructure = createDevToStructure(TITLE, DESCRIPTION, DATE, URL, TAG);

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<List<DevToStructure>> responseEntity = ResponseEntity.ok(Collections.singletonList(devToStructure));

        when(restTemplate.exchange(any(String.class), any(), eq(entity), any(Class.class)))
                .thenReturn(responseEntity);

        DevToDto devToDto = createDevToDto(1L, TITLE, DESCRIPTION, DATE, URL, TAG);
        when(devToMapper.toDto(any(DevTo.class))).thenReturn(devToDto);

        List<DevToDto> result = articleService.fetchArtcilesByTitle(title);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(TITLE, result.get(0).title());
        verify(devToRepository, times(1)).saveAll(any());
    }

    private DevToStructure createDevToStructure(String title, String description, String date, String url, String tags) {
        DevToStructure devToStructure = new DevToStructure();
        devToStructure.setTitle(title);
        devToStructure.setDescription(description);
        devToStructure.setDate(date);
        devToStructure.setUrl(url);
        devToStructure.setTags(tags);
        return devToStructure;
    }

    private DevToDto createDevToDto(Long id, String title, String description, String date, String url, String tags) {
        return new DevToDto(id, title, description, date, url, tags);
    }
}
