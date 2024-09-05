package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.request.ReviewCreateRequest;
import com.yamyamnavi.api.v1.response.ReviewDetailResponse;
import com.yamyamnavi.api.v1.response.ReviewResponse;
import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.support.response.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",  implementationName = "apiReviewConverterImpl")
public interface ReviewConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Review convertToReview(Long userId, ReviewCreateRequest request);

    ReviewResponse convertToReviewResponse(Review review);

    ReviewDetailResponse convertToReviewDetailResponse(Review source);

    List<ReviewDetailResponse> convertToReviewDetailResponse(List<Review> sources);


    default PageResponse<ReviewDetailResponse> convertToPageResponse(PageResponse<Review> pageReviews) {
        if (pageReviews == null) {
            return null;
        }
        List<ReviewDetailResponse> reviewDetailResponses = convertToReviewDetailResponse(pageReviews.getContents());
        PageResponse<ReviewDetailResponse> pageResponse = PageResponse.of(pageReviews.getCurrentPage(), pageReviews.getLimit(), pageReviews.getTotal(), reviewDetailResponses);
        return pageResponse;
    }

}