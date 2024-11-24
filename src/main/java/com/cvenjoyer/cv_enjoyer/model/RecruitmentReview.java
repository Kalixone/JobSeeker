package com.cvenjoyer.cv_enjoyer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "recruitment_reviews")
public class RecruitmentReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String position;
    private Integer stages;
    private String review;
    private Double rating;
    private LocalDateTime reviewDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}