package com.yamyamnavi.domain.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantReviewUpdater {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDetail updateReview(Long id, RestaurantDetail restaurantDetail) {

        RestaurantDetail updateReview = restaurantRepository.update(id, restaurantDetail);

        return updateReview;
    }
}
