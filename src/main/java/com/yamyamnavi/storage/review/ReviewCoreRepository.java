package com.yamyamnavi.storage.review;

import com.yamyamnavi.storage.review.converter.ReviewConverter;
import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.domain.review.ReviewRepository;
import com.yamyamnavi.support.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewCoreRepository implements ReviewRepository {

    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewRepositoryImpl reviewRepositoryImpl;
    private final ReviewConverter reviewConverter;

    @Override
    public Review save(Review review) {

        ReviewEntity reviewEntity = reviewConverter.convertToEntity(review);
        ReviewEntity savedReview = reviewJpaRepository.save(reviewEntity);
        return reviewConverter.convertToDomain(savedReview);
    }

    @Override
    public List<Review> findAllByRestaurantId(Long restaurantId) {
        return reviewRepositoryImpl.selectAllByRestaurantId(restaurantId);
    }

    @Override
    public PageResponse<Review> selectAllByRestaurantId(Long restaurantId, PageRequest pageRequest) {
        return reviewRepositoryImpl.selectAllByRestaurantId(restaurantId, pageRequest);
    }
}
