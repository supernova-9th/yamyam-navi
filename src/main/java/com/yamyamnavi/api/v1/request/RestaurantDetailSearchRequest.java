package com.yamyamnavi.api.v1.request;

import com.yamyamnavi.support.enums.ReviewSort;
import org.springframework.data.domain.Sort;

public record RestaurantDetailSearchRequest(
        ReviewSort sort, // 정렬 방식, 기본값은 'RECENT'
        int page,        // 페이지 번호, 기본값은 0
        int size         // 페이지 크기, 기본값은 10
) {
    public RestaurantDetailSearchRequest {
        if (sort == null) {
            sort = ReviewSort.RECENT;
        }
        if (page < 0) {
            page = 0;
        }
        if (size <= 0) {
            size = 10;
        }
    }

    public Sort toSort() {
        if (this.sort == ReviewSort.RATING) {
            return Sort.by(Sort.Direction.DESC, "score");
        } else {
            return Sort.by(Sort.Direction.DESC, "createdAt");
        }
    }
}
