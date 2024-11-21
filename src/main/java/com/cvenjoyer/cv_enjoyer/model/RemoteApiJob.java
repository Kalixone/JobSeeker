package com.cvenjoyer.cv_enjoyer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "remote_api_jobs")
public class RemoteApiJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("title")
    private String position;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("url")
    private String link;
    @JsonProperty("category")
    private String category;
    @JsonProperty("tags")
    @ElementCollection
    @CollectionTable(name = "job_frameworks", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "framework")
    private List<String> frameworks;
    @JsonProperty("job_type")
    private String jobType;
    @JsonProperty("publication_date")
    private LocalDate publicationDate;
    @JsonProperty("candidate_required_location")
    private String candidateRequiredLocation;
}
