package com.cvenjoyer.cv_enjoyer.controller;

import com.cvenjoyer.cv_enjoyer.dto.EmailDto;
import com.cvenjoyer.cv_enjoyer.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
@Tag(name = "Email Management", description = "Endpoints for managing user emails.")
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send-email")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Send Email", description = "Allows an authenticated user to send an email by providing the recipient, subject, and text.")
    public EmailDto sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String text,
            Authentication authentication) {

        return emailService.sendEmail(to, subject, text, authentication);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Get All User Emails", description = "Retrieves all emails associated with the authenticated user.")
    public List<EmailDto> getAllUsersEmails(Authentication authentication) {
        return emailService.getAllUsersEmails(authentication);
    }

    @GetMapping("/search-by-recipient")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search Emails by Recipient", description = "Finds emails sent by the authenticated user where the recipient contains the provided string (case insensitive).")
    public List<EmailDto> findByUserAndRecipientContainingIgnoreCase(Authentication authentication, @RequestParam String recipient) {
        return emailService.findByUserAndRecipientContainingIgnoreCase(authentication, recipient);
    }

    @GetMapping("/search-by-date")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Search Emails by Date Range", description = "Finds emails sent by the authenticated user within the specified date range.")
    public List<EmailDto> findByUserAndSentAtBetween(Authentication authentication,@RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate) {
        return emailService.findByUserAndSentAtBetween(authentication, startDate, endDate);
    }
}
