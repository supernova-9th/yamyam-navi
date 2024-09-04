package com.yamyamnavi.api.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserResponse(

        @Schema(description = "사용자 ID", example = "1L")
        Long id,

        @Schema(description = "사용자 이메일", example = "user@example.com")
        String email,

        @Schema(description = "사용자 위치의 위도", example = "37.5116")
        Double latitude,

        @Schema(description = "사용자 위치의 경도", example = "126.9272")
        Double longitude
)  {
}