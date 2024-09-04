package com.yamyamnavi.domain.review;

import com.yamyamnavi.support.response.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository {
    Review save(Review review);
    List<Review> findAllByRestaurantId(Long restaurantId);
    PageResponse<Review> selectAllByRestaurantId(Long id, PageRequest pageRequest);
}
