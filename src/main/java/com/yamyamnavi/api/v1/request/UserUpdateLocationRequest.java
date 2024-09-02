package com.yamyamnavi.api.v1.request;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateLocationRequest(
        @NotBlank(message = "주소는 필수입니다.")
        String address
) {}