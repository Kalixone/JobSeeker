package com.cvenjoyer.cv_enjoyer.dto;

import java.time.LocalDateTime;
public record EmailDto(
        Long id,
        String sender,
        String recipient,
        String subject,
        String content,
        LocalDateTime sentAt
) {
}
