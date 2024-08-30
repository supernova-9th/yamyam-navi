package com.yamyamnavi.api.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "시군구 정보 응답")
public record SggResponse(
        @Schema(description = "시군구 이름") String name,
        @Schema(description = "경도") double longitude,
        @Schema(description = "위도") double latitude
) {
}