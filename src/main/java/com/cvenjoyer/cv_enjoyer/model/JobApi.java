package com.cvenjoyer.cv_enjoyer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "job_api_jobs")
public class JobApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String position;
    private String companyName;
    private String link;
    private String category;
    @ElementCollection
    @CollectionTable(name = "job_tags2", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "tags")
    private Set<String> tags;
    @Enumerated(EnumType.STRING)
    private Job.JobType jobType;
    private LocalDate publicationDate;
    private String candidateRequiredLocation;
    private LocalDate applicationDate;
    private String location;
    private String salary;
}
