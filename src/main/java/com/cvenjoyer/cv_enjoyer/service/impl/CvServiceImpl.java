package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.CreateCvTemplateRequestDto;
import com.cvenjoyer.cv_enjoyer.service.CvService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CvServiceImpl implements CvService {

    @Override
    public File generateCvFile(CreateCvTemplateRequestDto requestDto) {
        Map<String, String> fields = Map.of(
                "name", requestDto.name(),
                "title", requestDto.title(),
                "profileDescription", requestDto.profileDescription(),
                "contactPhone", requestDto.contactPhone(),
                "contactEmail", requestDto.contactEmail(),
                "linkedin", requestDto.linkedin(),
                "github", requestDto.github(),
                "education", requestDto.education(),
                "skills", requestDto.skills(),
                "experience", requestDto.experience()
        );
        return createCvDocx(fields, requestDto.fileName());
    }

    private File createCvDocx(Map<String, String> fields, String fileName) {
        try {
            XWPFDocument document = new XWPFDocument();

            XWPFParagraph nameParagraph = document.createParagraph();
            nameParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun nameRun = nameParagraph.createRun();
            nameRun.setText(fields.get("name"));
            nameRun.setBold(true);
            nameRun.setFontSize(16);

            XWPFParagraph titleParagraph2 = document.createParagraph();
            titleParagraph2.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun titleRun2 = titleParagraph2.createRun();
            titleRun2.setText(fields.get("title"));
            titleRun2.setItalic(true);
            titleRun2.setFontSize(14);

            XWPFParagraph profileParagraph = document.createParagraph();
            profileParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun profileRun = profileParagraph.createRun();
            profileRun.setText("Profile:");
            profileRun.setBold(true);
            profileRun.addBreak();
            XWPFRun profileDescriptionRun = profileParagraph.createRun();
            profileDescriptionRun.setText(fields.get("profileDescription"));

            XWPFParagraph educationParagraph = document.createParagraph();
            educationParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun educationRun = educationParagraph.createRun();
            educationRun.setText("Education:");
            educationRun.setBold(true);
            educationRun.addBreak();
            XWPFRun educationDetailsRun = educationParagraph.createRun();
            educationDetailsRun.setText(fields.get("education"));

            XWPFParagraph skillsParagraph = document.createParagraph();
            skillsParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun skillsRun = skillsParagraph.createRun();
            skillsRun.setText("Skills:");
            skillsRun.setBold(true);
            skillsRun.addBreak();
            XWPFRun skillsDetailsRun = skillsParagraph.createRun();
            skillsDetailsRun.setText(fields.get("skills"));

            XWPFParagraph experienceParagraph = document.createParagraph();
            experienceParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun experienceRun = experienceParagraph.createRun();
            experienceRun.setText("Experience:");
            experienceRun.setBold(true);
            experienceRun.addBreak();
            XWPFRun experienceDetailsRun = experienceParagraph.createRun();
            experienceDetailsRun.setText(fields.get("experience"));

            XWPFParagraph contactParagraph = document.createParagraph();
            contactParagraph.setAlignment(ParagraphAlignment.LEFT);
            XWPFRun contactRun = contactParagraph.createRun();
            contactRun.setText("Contact Information:");
            contactRun.setBold(true);
            contactRun.addBreak();
            XWPFRun contactPhoneRun = contactParagraph.createRun();
            contactPhoneRun.setText("Phone: " + fields.get("contactPhone"));
            contactPhoneRun.addBreak();
            XWPFRun contactEmailRun = contactParagraph.createRun();
            contactEmailRun.setText("Email: " + fields.get("contactEmail"));
            contactEmailRun.addBreak();
            XWPFRun linkedinRun = contactParagraph.createRun();
            linkedinRun.setText("LinkedIn: " + fields.get("linkedin"));
            linkedinRun.addBreak();
            XWPFRun githubRun = contactParagraph.createRun();
            githubRun.setText("GitHub: " + fields.get("github"));

            String desktopPath = System.getProperty("user.home") + "/Desktop/GeneratedCVs";
            File folder = new File(desktopPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            if (fileName == null || fileName.trim().isEmpty()) {
                fileName = "cv_generated";
            }

            File outputFile = new File(folder, fileName + ".docx");

            try (FileOutputStream out = new FileOutputStream(outputFile)) {
                document.write(out);
            }

            return outputFile;
        } catch (IOException e) {
            throw new RuntimeException("Error while generating CV document", e);
        }
    }
}