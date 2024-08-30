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

    @Transactional(readOnly = true)
    public RestaurantDetail getRestaurantDetail(Long id) {
        RestaurantDetail detail = restaurantFinder.find(id);
        detail.updateReviewsAndScore(reviewFinder.findAll(id));
        return detail;
    }

}
