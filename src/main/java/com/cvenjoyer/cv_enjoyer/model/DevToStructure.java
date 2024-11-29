package com.cvenjoyer.cv_enjoyer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevToStructure {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("readable_publish_date")
    private String date;
    @JsonProperty("url")
    private String url;
    @JsonProperty("tags")
    private String tags;
}
