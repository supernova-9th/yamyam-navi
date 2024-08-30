package com.yamyamnavi.api.v1.response;

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
        double score
) {
}
