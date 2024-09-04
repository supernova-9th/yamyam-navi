package com.yamyamnavi.api.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserChangePasswordRequest(

        @Schema(description = "사용자 비밀번호", example = "newpassword123")
        @Size(min = 10, message = "비밀번호는 10자 이상이어야 합니다.")
        @Pattern(regexp = ".*[^0-9].*", message = "숫자로만 이루어진 비밀번호는 사용 불가합니다.")
        @NotBlank(message = "새로운 비밀번호를 입력하세요.")
        String newPassword
) {}