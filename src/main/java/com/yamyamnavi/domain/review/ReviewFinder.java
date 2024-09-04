package com.yamyamnavi.domain.review;

import com.yamyamnavi.support.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewFinder {

    private final ReviewRepository reviewRepository;

    public List<Review> findAll(Long restaurantId) {
        return reviewRepository.findAllByRestaurantId(restaurantId);
    }

    public PageResponse<Review> findReviews(Long id, PageRequest pageRequest) {
        return reviewRepository.selectAllByRestaurantId(id, pageRequest);
    }
}
