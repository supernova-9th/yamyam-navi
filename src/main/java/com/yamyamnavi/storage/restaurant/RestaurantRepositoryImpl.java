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
        return Optional.ofNullable(queryFactory.select(Projections.bean(RestaurantDetail.class
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
}
