package com.yamyamnavi.storage.review;

import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.domain.review.ReviewRepository;
import com.yamyamnavi.storage.review.converter.ReviewConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReviewCoreRepository implements ReviewRepository {

    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewConverter reviewConverter;

    @Override
    public Review save(Review review) {
        // 도메인 -> 엔티티
        ReviewEntity reviewEntity = reviewConverter.convertToEntity(review);

        // 엔티티 저장
        ReviewEntity savedReview = reviewJpaRepository.save(reviewEntity);

        // 엔티티 -> 도메인
        return reviewConverter.convertToDomain(savedReview);
    }
}
