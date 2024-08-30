package com.yamyamnavi.api.v1;

import com.yamyamnavi.api.v1.converter.ReviewConverter;
import com.yamyamnavi.api.v1.request.ReviewCreateRequest;
import com.yamyamnavi.api.v1.response.ReviewResponse;
import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.domain.review.ReviewService;
import com.yamyamnavi.support.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/reviews")
@Tag(name = "Review API", description = "리뷰 생성하는 API")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewConverter reviewConverter;

    /**
     * 새로운 리뷰를 생성합니다.
     *
     * @param request 생성할 리뷰의 정보가 담긴 ReviewCreateRequest 객체
     * @return 생성된 리뷰의 정보가 담긴 ReviewResponse 객체를 ResultResponse로 감싸서 반환
     */
    @PostMapping
    @Operation(summary = "새로운 리뷰 생성", description = "새로운 리뷰를 생성하고 생성된 리뷰를 반환합니다.")
    public ResultResponse<ReviewResponse> createReview(@RequestBody ReviewCreateRequest request) {

        Review review = reviewConverter.convertToDomain(request);
        Review savedReview = reviewService.createReview(review);
        ReviewResponse response = reviewConverter.convertToResponse(savedReview);

        return new ResultResponse<>(response);
    }
}
