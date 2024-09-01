package com.yamyamnavi.storage.review;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yamyamnavi.domain.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.yamyamnavi.storage.review.QReviewEntity.reviewEntity;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl {

    private final JPAQueryFactory queryFactory;


    public List<Review> selectAllByRestaurantId(Long restaurantId) {
        return queryFactory.select(Projections.constructor(Review.class
                , reviewEntity.id
                , reviewEntity.content
                , reviewEntity.score
                , reviewEntity.userId
                , reviewEntity.restaurantId
                , reviewEntity.createdAt
                , reviewEntity.updatedAt))
                .from(reviewEntity)
                .where(reviewEntity.restaurantId.eq(restaurantId))
                .orderBy(reviewEntity.createdAt.desc())
                .fetch();
    }
}
