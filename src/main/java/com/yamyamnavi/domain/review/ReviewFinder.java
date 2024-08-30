package com.yamyamnavi.domain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewFinder {

    private final ReviewRepository reviewRepository;

    public List<Review> findAll(Long restaurantId) {
        return reviewRepository.findAllByRestaurantId(restaurantId);
    }
}
