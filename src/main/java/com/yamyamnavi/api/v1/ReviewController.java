package com.yamyamnavi.api.v1;

import com.yamyamnavi.api.v1.converter.ReviewConverter;
import com.yamyamnavi.api.v1.request.ReviewCreateRequest;
import com.yamyamnavi.api.v1.response.ReviewResponse;
import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.domain.review.ReviewService;
import com.yamyamnavi.support.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewConverter reviewConverter;

    @PostMapping
    public ResultResponse<ReviewResponse> createReview(@RequestBody ReviewCreateRequest request) {

        // DTO -> review 도메인
        Review review = reviewConverter.convertToDomain(request);

        Review savedReview = reviewService.createReview(review);

        // review 도메인 -> DTO
        ReviewResponse responseDto = reviewConverter.convertToDomain(savedReview);

        return new ResultResponse<>(responseDto);
    }
}
