package com.yamyamnavi.api.v1.request;

import com.yamyamnavi.support.enums.RestaurantSort;

public record RestaurantSearchRequest(
        double lat,          // 위도
        double lon,          // 경도
        double range,        // 범위 (km 단위)
        RestaurantSort sort, // 정렬 방식, 기본값은 'DISTANCE'
        int page,            // 페이지 번호, 기본값은 0
        int size             // 페이지 크기, 기본값은 10
) {
    public RestaurantSearchRequest {
        if (sort == null) {
            sort = RestaurantSort.DISTANCE;
        }
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 10;
        }
    }
}
