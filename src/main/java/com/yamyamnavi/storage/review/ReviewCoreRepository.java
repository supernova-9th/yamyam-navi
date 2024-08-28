package com.yamyamnavi.storage.review;

import com.yamyamnavi.domain.review.ReviewRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReviewCoreRepository implements ReviewRepository {
    private final ReviewJpaRepository reviewJpaRepository;
}
