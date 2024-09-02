package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.request.ReviewCreateRequest;
import com.yamyamnavi.api.v1.response.ReviewResponse;
import com.yamyamnavi.domain.review.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",  implementationName = "apiReviewConverterImpl")
public interface ReviewConverter {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Review convertToReview(ReviewCreateRequest request);

    ReviewResponse convertToReviewResponse(Review review);

}