package com.yamyamnavi.api.v1.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "리뷰 정보 응답")
public record ReviewResponse (
    @Schema(description = "아이디") Long id,
    @Schema(description = "내용") String content,
    @Schema(description = "평점") Integer score,
    @Schema(description = "작성자 아이디") Long userId,
    @Schema(description = "가게 아이디") Long restaurantId,
    @Schema(description = "작성 일시") LocalDateTime createdAt
){}