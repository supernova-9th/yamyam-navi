package com.yamyamnavi.storage.review;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.support.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.querydsl.core.types.Order.*;
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

    public PageResponse<Review> selectAllByRestaurantId(Long restaurantId, PageRequest pageRequest) {
        PathBuilder<Review> entityPath = new PathBuilder<>(Review.class, "reviewEntity");
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        if (pageRequest.getSort().getOrderFor("score") != null) {
            orderSpecifiers.add(new OrderSpecifier<>(DESC, entityPath.getNumber("score", Double.class)));
            orderSpecifiers.add(new OrderSpecifier<>(DESC, entityPath.getDate("createdAt", java.util.Date.class)));
        } else {
            orderSpecifiers.add(new OrderSpecifier<>(DESC, entityPath.getDate("createdAt", java.util.Date.class)));
        }

        JPAQuery<Review> query = queryFactory.select(Projections.constructor(Review.class
                        , reviewEntity.id
                        , reviewEntity.content
                        , reviewEntity.score
                        , reviewEntity.userId
                        , reviewEntity.restaurantId
                        , reviewEntity.createdAt
                        , reviewEntity.updatedAt))
                .from(reviewEntity)
                .where(reviewEntity.restaurantId.eq(restaurantId));

        List<Review> reviews = query
                .orderBy(orderSpecifiers.toArray(new OrderSpecifier<?>[0]))
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        long total = query.fetchCount();

        Page<Review> page = new PageImpl<>(reviews, pageRequest, total);

        return PageResponse.of(page);
    }
}
