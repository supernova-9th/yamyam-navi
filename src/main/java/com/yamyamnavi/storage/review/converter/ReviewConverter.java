package com.yamyamnavi.storage.review.converter;

import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.storage.review.ReviewEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationName = "storageReviewConverterImpl")
public interface ReviewConverter {

    Review convertToDomain(ReviewEntity resources);

    ReviewEntity convertToEntity(Review review);
}
