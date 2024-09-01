package com.yamyamnavi.domain.restaurant;

import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.domain.review.ReviewFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantReviewUpdater {

    private final RestaurantRepository restaurantRepository;
    private final ReviewFinder reviewFinder;

    public RestaurantDetail updateReview(Long id, RestaurantDetail restaurantDetail) {

        // 해당 가게의 모든 리뷰 조회
        List<Review> reviews = reviewFinder.findAll(id);

        // 평균 점수 계산 및 저장
        restaurantDetail.updateReviewsAndScore(reviews);

        // 해당 가게 리뷰 업데이트
        RestaurantDetail updateReview = restaurantRepository.update(id, restaurantDetail);

        return updateReview;
    }
}
