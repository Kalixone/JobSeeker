package com.cvenjoyer.cv_enjoyer.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "user_statistics")
public class UserStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    private Long totalApplications;
    private Long expiredJobsCount;
    private Long rejectedJobsCount;
    private Long appliedJobsCount;
    @Enumerated(EnumType.STRING)
    private Job.JobType preferredJobType;
}
