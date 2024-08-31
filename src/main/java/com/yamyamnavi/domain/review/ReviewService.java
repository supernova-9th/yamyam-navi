package com.yamyamnavi.domain.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewAppender reviewAppender;

    /**
     * 새로운 리뷰를 생성하고 저장합니다.
     *
     * @param review 저장할 리뷰 도메인 객체
     * @return 저장된 리뷰 도메인 객체
     */
    @Transactional
    public Review createReview(Review review) {
        return reviewAppender.append(review);
    }

}

