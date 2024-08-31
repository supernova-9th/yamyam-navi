package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.request.ReviewCreateRequest;
import com.yamyamnavi.api.v1.response.ReviewResponse;
import com.yamyamnavi.domain.review.Review;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        apiReviewConverterImpl.class
})
class ReviewConverterTest {

    @Autowired
    private ReviewConverter reviewConverter;

    @Test
    void ReviewCreateRequest를_Review로_변환한다() {
        //given
        ReviewCreateRequest request = ReviewCreateRequest.builder()
                .userId(1L)
                .restaurantId(1L)
                .content("goood")
                .score(3)
                .build();
        Review expect = new Review(1L, 1L, 1L,"goood", 3, LocalDateTime.now());

        //when
        Review result = reviewConverter.convertToReview(request);

        //then
        assertEquals(expect.getUserId(), result.getUserId());
        assertEquals(expect.getRestaurantId(), result.getRestaurantId());
        assertEquals(expect.getContent(), result.getContent());
        assertEquals(expect.getScore(), result.getScore());
    }

    @Test
    void Review를_ReviewResponse로_변환한다() {
        //given
        Review review = new Review(1L, 1L, 1L, "goood", 4, LocalDateTime.now());
        ReviewResponse expect = new ReviewResponse(1L, "goood", 4,1L, 1L, LocalDateTime.now());

        //when
        ReviewResponse result = reviewConverter.convertToReviewResponse(review);

        //then
        assertEquals(expect.content(), result.content());
        assertEquals(expect.score(), result.score());
        assertEquals(expect.createdAt(), result.createdAt());
    }

}