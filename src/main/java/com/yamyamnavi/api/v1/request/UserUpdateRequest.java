package com.yamyamnavi.api.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(

        @Schema(description = "사용자 비밀번호", example = "newpassword987")
        @Size(min = 10, message = "비밀번호는 10자 이상이어야 합니다.")
        @Pattern(regexp = ".*[^0-9].*", message = "숫자로만 이루어진 비밀번호는 사용 불가합니다.")
        String password,

        @Schema(description = "사용자 주소", example = "경상남도 합천군 가야면 치인리 10")
        @NotBlank(message = "주소는 필수입니다.")
        String address
) {}