package com.cvenjoyer.cv_enjoyer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NominatimResponse {
    @JsonProperty("lat")
    private double latitude;
    @JsonProperty("lon")
    private double longitude;
}
