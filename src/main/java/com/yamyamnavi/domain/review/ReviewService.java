package com.yamyamnavi.domain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewAppender reviewAppender;

    public Review createReview(Review review) {
        return reviewAppender.append(review);
    }

}

