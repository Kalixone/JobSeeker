package com.cvenjoyer.cv_enjoyer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RemotiveJob {
    @JsonProperty("title")
    private String position;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("url")
    private String link;

    @JsonProperty("category")
    private String category;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("job_type")
    private String jobType;

    @JsonProperty("publication_date")
    private LocalDate publicationDate;

    @JsonProperty("candidate_required_location")
    private String candidateRequiredLocation;
    @JsonProperty("salary")
    private String salary;
}
