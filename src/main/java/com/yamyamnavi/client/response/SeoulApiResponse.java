package com.yamyamnavi.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SeoulApiResponse(LOCALDATA_072404 LOCALDATA_072404) {
    public record LOCALDATA_072404(List<SeoulFacility> row,
                                   @JsonProperty("RESULT")
                                   Result result) {}

    public record Result(@JsonProperty("CODE") String code, @JsonProperty("MESSAGE") String message) {}

}