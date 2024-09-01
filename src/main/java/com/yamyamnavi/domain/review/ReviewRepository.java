package com.yamyamnavi.domain.review;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository {
    Review save(Review review);
    List<Review> findAllByRestaurantId(Long restaurantId);
}
