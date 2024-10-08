package com.yamyamnavi.api.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
        @Schema(description = "사용자 이메일", example = "user@example.com")
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
        String email,

        @Schema(description = "사용자 비밀번호", example = "password123")
        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).*$", message = "비밀번호는 숫자와 영문자를 모두 포함해야 합니다.")
        String password,

        @Schema(description = "사용자 주소", example = "서울특별시 동작구 여의대방로54길 18")
        @NotBlank(message = "주소는 필수입니다.")
        String address
) {}