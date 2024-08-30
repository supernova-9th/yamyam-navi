package com.yamyamnavi.api.v1.response;

import com.yamyamnavi.domain.review.Review;

import java.util.List;

public record RestaurantResponse(
        Long id,
        String name,
        String jibeonAddress,
        String roadAddress,
        double longitude,
        double latitude,
        String category,
        Boolean isBusinessActive,
        String telephone,
        double score,
        List<ReviewResponse> reviews
) {
}
