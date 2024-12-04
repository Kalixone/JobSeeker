package com.cvenjoyer.cv_enjoyer.repository;

import com.cvenjoyer.cv_enjoyer.model.Email;
import com.cvenjoyer.cv_enjoyer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
    List<Email> findByUser(User user);
    List<Email> findByUserAndRecipientContainingIgnoreCase(User user, String recipient);
    List<Email> findByUserAndSentAtBetween(User user, LocalDateTime startDate, LocalDateTime endDate);
}
