package com.cvenjoyer.cv_enjoyer.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String position;
    private String location;
    private String salary;
    private LocalDate applicationDate;
    @Enumerated(EnumType.STRING)
    private JobStatus jobStatus;
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    private LocalDate feedBackDate;
    private LocalDate interviewDate;
    private String link;
    private String companyWebsite;
    private String contactEmail;
    private Double kilometers;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "job_tags", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "tags")
    private Set<String> tags;
    private String notes;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public enum JobStatus {
        APPLIED,
        EXPIRED,
        REJECTED,
        INTERVIEW
    }

    public enum JobType {
        STATIONARY,
        HYBRID,
        REMOTE,
        FULL_TIME,
        CONTRACT
    }
}
