package com.yamyamnavi.api.v1.response;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "맛집 정보 응답")
public record RestaurantDetailResponse(
        @Schema(description = "아이디") Long id,
        @Schema(description = "매장명") String name,
        @Schema(description = "지번 주소") String jibeonAddress,
        @Schema(description = "도로명 주소") String roadAddress,
        @Schema(description = "경도") double longitude,
        @Schema(description = "위도") double latitude,
        @Schema(description = "카테고리") String category,
        @Schema(description = "영업 상태") Boolean isBusinessActive,
        @Schema(description = "전화번호") String telephone,
        @Schema(description = "평점") double score,
        @Schema(description = "리뷰 목록") List<ReviewDetailResponse> reviews
) {
}