package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.CreateCvTemplateRequestDto;
import com.cvenjoyer.cv_enjoyer.service.CvService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cv")
public class CvController {
    private final CvService cvService;

    @PostMapping("/generate")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Generate CV", description = "Generates a CV file based on the provided data and returns it as a downloadable file.")
    public ResponseEntity<byte[]> generateCv(@RequestBody CreateCvTemplateRequestDto requestDto) {
        try {
            File file = cvService.generateCvFile(requestDto);
            byte[] fileContent = Files.readAllBytes(file.toPath());

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=cv.docx");
            headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);

        } catch (Exception e) {
            throw new RuntimeException("Error generating CV", e);
        }
    }
}
