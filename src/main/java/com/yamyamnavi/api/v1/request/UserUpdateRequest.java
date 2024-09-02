package com.yamyamnavi.api.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @Schema(description = "사용자 비밀번호", example = "newpassword123")
        @Size(min = 10, message = "비밀번호는 10자 이상이어야 합니다.")
        @Pattern(regexp = ".*[^0-9].*", message = "숫자로만 이루어진 비밀번호는 사용 불가합니다.")
        String password,

        @Schema(description = "사용자 주소", example = "서울특별시 동작구 여의대방로54길 18")
        @NotBlank(message = "주소는 필수입니다.")
        String address
) {}