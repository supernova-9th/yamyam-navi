package com.yamyamnavi.storage.restaurant;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.yamyamnavi.storage.restaurant.QRestaurantEntity.*;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public Optional<RestaurantDetail> selectRestaurantDetail(Long id) {
        return Optional.ofNullable(queryFactory.select(Projections.constructor(RestaurantDetail.class
                , restaurantEntity.id
                , restaurantEntity.name
                , restaurantEntity.jibeonAddress
                , restaurantEntity.roadAddress
                , restaurantEntity.location
                , restaurantEntity.category
                , restaurantEntity.isBusinessActive
                , restaurantEntity.telephone
                ))
                .from(restaurantEntity)
                .where(restaurantEntity.id.eq(id))
                .fetchOne());
    }

    public Optional<RestaurantDetail> updateReview(Long id, RestaurantDetail restaurantDetail) {
        long updatedRows = queryFactory.update(restaurantEntity)
                .set(restaurantEntity.score, restaurantDetail.getScore())
                .where(restaurantEntity.id.eq(id))
                .execute();

        if (updatedRows > 0) {
            return Optional.ofNullable(queryFactory.select(Projections.constructor(RestaurantDetail.class
                            , restaurantEntity.id
                            , restaurantEntity.name
                            , restaurantEntity.jibeonAddress
                            , restaurantEntity.roadAddress
                            , restaurantEntity.location
                            , restaurantEntity.category
                            , restaurantEntity.isBusinessActive
                            , restaurantEntity.telephone
                            , restaurantEntity.score
                    ))
                    .from(restaurantEntity)
                    .where(restaurantEntity.id.eq(id))
                    .fetchOne());
        } else {
            return Optional.empty();
        }
    }
}
