package com.yamyamnavi.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SeoulApiResponse(
        @JsonProperty("LOCALDATA_072404")
        LOCALDATA_072404 localData072404,
        @JsonProperty("RESULT")
        Result result
) {
    public record LOCALDATA_072404(
            List<SeoulFacility> row
    ) {}

    public record Result(
            @JsonProperty("CODE") String code,
            @JsonProperty("MESSAGE") String message
    ) {}

    public boolean isNoDataResponse() {
        return result != null && "INFO-200".equals(result.code());
    }
}