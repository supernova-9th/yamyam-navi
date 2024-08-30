package com.yamyamnavi.domain.review;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewAppender {

    private final ReviewRepository reviewRepository;

    public Review append(Review review) {

        Review savedReview = reviewRepository.save(review);

        return savedReview;
    }
}
