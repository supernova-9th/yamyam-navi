package com.yamyamnavi.domain.restaurant;

import com.yamyamnavi.domain.review.ReviewFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantFinder restaurantFinder;
    private final ReviewFinder reviewFinder;

    /**
     * 맛집의 상세 정보를 조회합니다.
     *
     * @param id 조회할 맛집 아이디
     * @return 조회된 맛집의 상세 정보가 담긴 RestaurantDetail 객체
     */
    @Transactional(readOnly = true)
    public RestaurantDetail getRestaurantDetail(Long id) {
        RestaurantDetail detail = restaurantFinder.find(id);
        detail.updateReviewsAndScore(reviewFinder.findAll(id));
        return detail;
    }

}
