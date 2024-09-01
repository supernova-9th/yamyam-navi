package com.yamyamnavi.storage.review;

import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.domain.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
public class ReviewCoreRepository implements ReviewRepository {
    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewRepositoryImpl reviewRepositoryImpl;

    @Override
    public List<Review> findAllByRestaurantId(Long restaurantId) {
        return reviewRepositoryImpl.selectAllByRestaurantId(restaurantId);
    }
}
