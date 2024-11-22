package com.cvenjoyer.cv_enjoyer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RemotiveJobResponse {

    @JsonProperty("jobs")
    private List<RemotiveJob> jobs;
}
