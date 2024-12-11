package com.cvenjoyer.cv_enjoyer.service.impl;

import com.cvenjoyer.cv_enjoyer.dto.EmailDto;
import com.cvenjoyer.cv_enjoyer.mapper.EmailMapper;
import com.cvenjoyer.cv_enjoyer.model.Email;
import com.cvenjoyer.cv_enjoyer.model.User;
import com.cvenjoyer.cv_enjoyer.repository.EmailRepository;
import com.cvenjoyer.cv_enjoyer.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;
    private final EmailRepository emailSenderRepository;
    private final EmailMapper emailMapper;

    @Override
    public EmailDto sendEmail(String to, String subject, String text, Authentication authentication) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.setFrom("your-email@example.com");

            mailSender.send(message);

            Email email = new Email();
            email.setSender("your-email@example.com");
            email.setRecipient(to);
            email.setSubject(subject);
            email.setContent(text);
            email.setSentAt(LocalDateTime.now());
            email.setUser(authentication != null ? (User) authentication.getPrincipal() : null);

            emailSenderRepository.save(email);
            return emailMapper.toDto(email);
        } catch (MessagingException e) {
            throw new RuntimeException("Error while sending email", e);
        }
    }

    @Override
    public List<EmailDto> getAllUsersEmails(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return emailSenderRepository.findByUser(user).stream().map(emailMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EmailDto> findByUserAndRecipientContainingIgnoreCase(Authentication authentication, String recipient) {
        User user = (User) authentication.getPrincipal();
        return emailSenderRepository.findByUserAndRecipientContainingIgnoreCase(user, recipient).stream().map(emailMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EmailDto> findByUserAndSentAtBetween(Authentication authentication, LocalDateTime startDate, LocalDateTime endDate) {
        User user = (User) authentication.getPrincipal();
        return emailSenderRepository.findByUserAndSentAtBetween(user, startDate, endDate).stream().map(emailMapper::toDto).collect(Collectors.toList());
    }
}
