package com.yamyamnavi.storage.review.converter;

import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.storage.review.ReviewEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        storageReviewConverterImpl.class
})
class ReviewConverterTest {

    @Autowired
    private ReviewConverter reviewConverter;

    @Test
    void ReviewEntity를_Review로_변환한다() {
        //given
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .restaurantId(1L)
                .userId(1L)
                .content("goood")
                .score(4)
                .build();
        Review expect = new Review(1L, 1L, 1L, "goood", 4, LocalDateTime.now(), LocalDateTime.now());

        //when
        Review result = reviewConverter.convertToDomain(reviewEntity);

        //then
        assertEquals(expect.getUserId(), result.getUserId());
        assertEquals(expect.getRestaurantId(), result.getRestaurantId());
        assertEquals(expect.getContent(), result.getContent());
        assertEquals(expect.getScore(), result.getScore());
    }

    @Test
    void Review를_ReviewEntity로_변환한다() {
        //given
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .restaurantId(1L)
                .userId(1L)
                .content("goood")
                .score(4)
                .build();
        Review expect = new Review(1L, 1L, 1L, "goood", 4, LocalDateTime.now(), LocalDateTime.now());

        //when
        Review result = reviewConverter.convertToDomain(reviewEntity);

        //then
        assertEquals(expect.getUserId(), result.getUserId());
        assertEquals(expect.getRestaurantId(), result.getRestaurantId());
        assertEquals(expect.getContent(), result.getContent());
        assertEquals(expect.getScore(), result.getScore());
    }
}