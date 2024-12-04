package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.EmailDto;
import com.cvenjoyer.cv_enjoyer.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send-email")
    @PreAuthorize("hasAuthority('USER')")
    public EmailDto sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String text,
            Authentication authentication) {

        return emailService.sendEmail(to, subject, text, authentication);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<EmailDto> getAllUsersEmails(Authentication authentication) {
        return emailService.getAllUsersEmails(authentication);
    }

    @GetMapping("/search-by-recipient")
    @PreAuthorize("hasAuthority('USER')")
    public List<EmailDto> findByUserAndRecipientContainingIgnoreCase(Authentication authentication, @RequestParam String recipient) {
        return emailService.findByUserAndRecipientContainingIgnoreCase(authentication, recipient);
    }

    @GetMapping("/search-by-date")
    @PreAuthorize("hasAuthority('USER')")
    public List<EmailDto> findByUserAndSentAtBetween(Authentication authentication,@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return emailService.findByUserAndSentAtBetween(authentication, startDate, endDate);
    }
}
