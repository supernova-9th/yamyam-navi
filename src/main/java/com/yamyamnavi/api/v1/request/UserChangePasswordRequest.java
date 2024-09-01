package com.yamyamnavi.api.v1.request;

import jakarta.validation.constraints.NotBlank;

public record UserChangePasswordRequest(
        @NotBlank(message = "새로운 비밀번호를 입력하세요.")
        String newPassword
) {}