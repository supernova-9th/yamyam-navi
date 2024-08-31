package com.yamyamnavi.api.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "도시 정보 응답")
public record CityResponse(
        @Schema(description = "도시 이름") String city,
        @Schema(description = "시군구 목록") List<SggResponse> sgg
) {
}