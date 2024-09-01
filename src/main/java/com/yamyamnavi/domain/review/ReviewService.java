package com.yamyamnavi.domain.review;

import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import com.yamyamnavi.domain.restaurant.RestaurantFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewAppender reviewAppender;
    private final RestaurantFinder restaurantFinder;
    private final ReviewFinder reviewFinder;

    /**
     * 새로운 리뷰를 생성하고 저장합니다.
     *
     * @param review 저장할 리뷰 도메인 객체
     * @return 저장된 리뷰 도메인 객체
     */
    @Transactional
    public Review createReview(Review review) {
        // 가게 정보 조회
        RestaurantDetail detail = restaurantFinder.find(review.getRestaurantId());

        // 해당 가게의 모든 리뷰 조회 및 평균 점수 계산
        List<Review> reviews = reviewFinder.findAll(review.getRestaurantId());
        detail.updateReviewsAndScore(reviews);

        // 리뷰 정보 저장
        return reviewAppender.append(review);
    }

}

