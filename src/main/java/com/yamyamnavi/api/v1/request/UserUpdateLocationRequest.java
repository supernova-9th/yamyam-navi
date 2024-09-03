package com.yamyamnavi.api.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateLocationRequest(

        @Schema(description = "사용자 주소", example = "제주특별자치도 제주시 특별자치도, 공항로 2")
        @NotBlank(message = "주소는 필수입니다.")
        String address
) {}