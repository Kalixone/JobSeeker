package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.DevToDto;
import com.cvenjoyer.cv_enjoyer.mapper.DevToMapper;
import com.cvenjoyer.cv_enjoyer.model.*;
import com.cvenjoyer.cv_enjoyer.repository.DevToRepository;
import com.cvenjoyer.cv_enjoyer.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final RestTemplate restTemplate;
    private final DevToRepository devToRepository;
    private final DevToMapper devToMapper;

    @Override
    public List<DevToDto> fetchAllArticles(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        Set<String> programmingLanguages = principal.getProgrammingLanguages();
        Set<String> frameworks = principal.getFrameworks();
        Set<String> languagesAndFrameworks = new HashSet<>();
        languagesAndFrameworks.addAll(programmingLanguages);
        languagesAndFrameworks.addAll(frameworks);

        List<DevToStructure> devToStructures = new ArrayList<>();

        for (String tag : languagesAndFrameworks) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            String url = "https://dev.to/api/articles?tag=" + tag.toLowerCase() + "&per_page=40";

            ResponseEntity<List<DevToStructure>> responseEntity = restTemplate.exchange(
                    url,
                    org.springframework.http.HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<DevToStructure>>() {}
            );

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                List<DevToStructure> articles = responseEntity.getBody();
                if (articles != null) {
                    devToStructures.addAll(articles);
                }
            } else {
                System.out.println("Response Error: " + responseEntity.getStatusCode());
            }
        }

        List<DevTo> collect = devToStructures.stream().map(a -> {
            DevTo devTo = new DevTo();
            devTo.setTitle(a.getTitle());
            devTo.setDescription(a.getDescription());
            devTo.setDate(a.getDate());
            devTo.setTags(a.getTags());
            devTo.setUrl(a.getUrl());
            return devTo;
        }).collect(Collectors.toList());

        devToRepository.saveAll(collect);

        return collect.stream().map(devToMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DevToDto> fetchArticlesByKeyWord(String word) {
        List<DevToStructure> devToStructures = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://dev.to/api/articles?tag=" + word.toLowerCase() + "&per_page=40";

        ResponseEntity<List<DevToStructure>> responseEntity = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<DevToStructure>>() {}
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<DevToStructure> articles = responseEntity.getBody();
            if (articles != null) {
                devToStructures.addAll(articles);
            }
        } else {
            System.out.println("Response Error: " + responseEntity.getStatusCode());
        }

        List<DevTo> collect = devToStructures.stream().map(a -> {
            DevTo devTo = new DevTo();
            devTo.setTitle(a.getTitle());
            devTo.setDescription(a.getDescription());
            devTo.setDate(a.getDate());
            devTo.setTags(a.getTags());
            devTo.setUrl(a.getUrl());
            return devTo;
        }).collect(Collectors.toList());

        devToRepository.saveAll(collect);

        return collect.stream().map(devToMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DevToDto> fetchArtcilesByTitle(String title) {
        List<DevToStructure> devToStructures = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://dev.to/api/articles?per_page=40";

        ResponseEntity<List<DevToStructure>> responseEntity = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<DevToStructure>>() {}
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<DevToStructure> articles = responseEntity.getBody();
            if (articles != null) {
                devToStructures.addAll(articles);
            }
        } else {
            System.out.println("Response Error: " + responseEntity.getStatusCode());
        }

        List<DevToStructure> filteredArticles = devToStructures.stream()
                .filter(article -> article.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());

        List<DevTo> collect = filteredArticles.stream().map(a -> {
            DevTo devTo = new DevTo();
            devTo.setTitle(a.getTitle());
            devTo.setDescription(a.getDescription());
            devTo.setDate(a.getDate());
            devTo.setTags(a.getTags());
            devTo.setUrl(a.getUrl());
            return devTo;
        }).collect(Collectors.toList());

        devToRepository.saveAll(collect);

        return collect.stream().map(devToMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<DevToDto> fetchArtcilesByDescription(String description) {
        List<DevToStructure> devToStructures = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String url = "https://dev.to/api/articles?per_page=40";

        ResponseEntity<List<DevToStructure>> responseEntity = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<DevToStructure>>() {}
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            List<DevToStructure> articles = responseEntity.getBody();
            if (articles != null) {
                devToStructures.addAll(articles);
            }
        } else {
            System.out.println("Response Error: " + responseEntity.getStatusCode());
        }

        List<DevToStructure> filteredArticles = devToStructures.stream()
                .filter(article -> article.getDescription().toLowerCase().contains(description.toLowerCase()))
                .collect(Collectors.toList());

        List<DevTo> collect = filteredArticles.stream().map(a -> {
            DevTo devTo = new DevTo();
            devTo.setTitle(a.getTitle());
            devTo.setDescription(a.getDescription());
            devTo.setDate(a.getDate());
            devTo.setTags(a.getTags());
            devTo.setUrl(a.getUrl());
            return devTo;
        }).collect(Collectors.toList());

        devToRepository.saveAll(collect);

        return collect.stream().map(devToMapper::toDto).collect(Collectors.toList());
    }
}
