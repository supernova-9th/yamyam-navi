package com.yamyamnavi.storage.review;

import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.domain.review.ReviewRepository;
import com.yamyamnavi.storage.review.converter.ReviewConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Component
public class ReviewCoreRepository implements ReviewRepository {

    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewConverter reviewConverter;
    private final ReviewRepositoryImpl reviewRepositoryImpl;


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
}
