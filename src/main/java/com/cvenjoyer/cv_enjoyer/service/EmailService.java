package com.cvenjoyer.cv_enjoyer.service;

import com.cvenjoyer.cv_enjoyer.dto.EmailDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface EmailService {
    EmailDto sendEmail(String to, String subject, String text, Authentication authentication);
    List<EmailDto> getAllUsersEmails(Authentication authentication);
    List<EmailDto> findByUserAndRecipientContainingIgnoreCase(Authentication authentication, String recipient);
    List<EmailDto> findByUserAndSentAtBetween(Authentication authentication, LocalDateTime startDate, LocalDateTime endDate);
}
