package com.yamyamnavi.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SeoulFacility(
        @JsonProperty("BPLCNM")
        String name,

        @JsonProperty("DTLSTATENM")
        String businessStatusName,

        @JsonProperty("SITETEL")
        String telephone,

        @JsonProperty("SITEWHLADDR")
        String jibeonAddress,

        @JsonProperty("RDNWHLADDR")
        String roadAddress,

        @JsonProperty("UPTAENM")
        String category,

        @JsonProperty("X")
        String x,

        @JsonProperty("Y")
        String y
) {
        public boolean isActive() {
                return businessStatusName.equals("영업");
        }

        public String getUniqueName() {
                return name + " " + x + " " + y;
        }
}