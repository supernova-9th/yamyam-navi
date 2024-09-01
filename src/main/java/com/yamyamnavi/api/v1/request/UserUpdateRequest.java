package com.yamyamnavi.api.v1.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

public record UserUpdateRequest(
        @Schema(description = "사용자 비밀번호", example = "newpassword123")
        @Size(min = 10, message = "비밀번호는 10자 이상이어야 합니다.")
        @Pattern(regexp = ".*[^0-9].*", message = "숫자로만 이루어진 비밀번호는 사용 불가합니다.")
        String password,

        @Schema(description = "사용자 위치의 위도", example = "37.5665")
        Double latitude,

        @Schema(description = "사용자 위치의 경도", example = "126.9780")
        Double longitude
) {}