package com.yamyamnavi.api.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest(

        @Schema(description = "사용자 이메일", example = "user@example.com")
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
        String email,

        @Schema(description = "사용자 비밀번호", example = "password123")
        @NotBlank(message = "비밀번호는 필수입니다.")
        String password
) {}