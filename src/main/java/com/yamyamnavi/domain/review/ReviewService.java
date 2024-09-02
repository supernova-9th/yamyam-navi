package com.yamyamnavi.domain.review;

import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import com.yamyamnavi.domain.restaurant.RestaurantFinder;
import com.yamyamnavi.domain.restaurant.RestaurantReviewUpdater;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewAppender reviewAppender;
    private final RestaurantFinder restaurantFinder;
    private final RestaurantReviewUpdater restaurantReviewUpdater;

    /**
     * 새로운 리뷰를 생성하고 저장합니다.
     * 가게의 모든 리뷰를 조회하여 평균 점수를 계산하고 업데이트합니다.
     *
     * @param review 저장할 리뷰 도메인 객체
     * @return 저장된 리뷰 도메인 객체
     */
    @Transactional
    public Review createReview(Review review) {

        // 가게 정보 조회
        RestaurantDetail detail = restaurantFinder.find(review.getRestaurantId());

        // 리뷰 저장
        Review savedReview = reviewAppender.append(review);

        // 리뷰 업데이트
        restaurantReviewUpdater.updateReview(review.getRestaurantId(), detail, review.getScore());

        // 리뷰 정보 저장
        return savedReview;

    }

}

