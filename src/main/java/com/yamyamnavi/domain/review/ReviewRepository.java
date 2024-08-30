package com.yamyamnavi.domain.review;

import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository {
    Review save(Review review);
}
