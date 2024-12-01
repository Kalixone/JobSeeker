package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.CreateCvTemplateRequestDto;
import com.cvenjoyer.cv_enjoyer.service.impl.CvServiceImpl;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CvServiceTest {

    private static final String NAME = "John Doe";
    private static final String TITLE = "Software Engineer";
    private static final String PROFILE_DESCRIPTION = "Experienced Java developer";
    private static final String CONTACT_PHONE = "123-456-789";
    private static final String CONTACT_EMAIL = "john.doe@example.com";
    private static final String LINKEDIN = "https://www.linkedin.com/in/johndoe";
    private static final String GITHUB = "https://github.com/johndoe";
    private static final String EDUCATION = "Bachelor's in Computer Science";
    private static final String SKILLS = "Java, Spring, Hibernate";
    private static final String EXPERIENCE = "5 years of software development";
    private static final String FILE_NAME = "john_doe_cv";

    @InjectMocks
    private CvServiceImpl cvService;
    @Mock
    private CreateCvTemplateRequestDto createCvTemplateRequestDto;

    @Test
    @DisplayName("generateCvFile returns generated CV file with correct format")
    void generateCvFile_ReturnsGeneratedCvFileWithCorrectFormat() throws IOException {
        when(createCvTemplateRequestDto.name()).thenReturn(NAME);
        when(createCvTemplateRequestDto.title()).thenReturn(TITLE);
        when(createCvTemplateRequestDto.profileDescription()).thenReturn(PROFILE_DESCRIPTION);
        when(createCvTemplateRequestDto.contactPhone()).thenReturn(CONTACT_PHONE);
        when(createCvTemplateRequestDto.contactEmail()).thenReturn(CONTACT_EMAIL);
        when(createCvTemplateRequestDto.linkedin()).thenReturn(LINKEDIN);
        when(createCvTemplateRequestDto.github()).thenReturn(GITHUB);
        when(createCvTemplateRequestDto.education()).thenReturn(EDUCATION);
        when(createCvTemplateRequestDto.skills()).thenReturn(SKILLS);
        when(createCvTemplateRequestDto.experience()).thenReturn(EXPERIENCE);
        when(createCvTemplateRequestDto.fileName()).thenReturn(FILE_NAME);

        File generatedCv = cvService.generateCvFile(createCvTemplateRequestDto);

        assertNotNull(generatedCv);
        assertTrue(generatedCv.exists());
        assertTrue(generatedCv.getName().endsWith(".docx"));

        try (XWPFDocument document = new XWPFDocument(Files.newInputStream(generatedCv.toPath()))) {
            assertNotNull(document);
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(NAME)));
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(TITLE)));
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(PROFILE_DESCRIPTION)));
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(CONTACT_PHONE)));
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(CONTACT_EMAIL)));
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(LINKEDIN)));
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(GITHUB)));
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(EDUCATION)));
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(SKILLS)));
            assertTrue(document.getParagraphs().stream().anyMatch(p -> p.getText().contains(EXPERIENCE)));
        }
    }
}
