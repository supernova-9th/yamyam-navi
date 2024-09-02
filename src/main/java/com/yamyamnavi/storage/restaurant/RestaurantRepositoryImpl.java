package com.yamyamnavi.storage.restaurant;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yamyamnavi.api.v1.request.RestaurantSearchRequest;
import com.yamyamnavi.domain.restaurant.Restaurant;
import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import com.yamyamnavi.support.enums.RestaurantSort;
import com.yamyamnavi.support.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.yamyamnavi.storage.restaurant.QRestaurantEntity.*;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl {

    private final JPAQueryFactory queryFactory;

    public PageResponse<Restaurant> selectRestaurants(RestaurantSearchRequest searchRequest, PageRequest pageRequest) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Point userLocation = geometryFactory.createPoint(new Coordinate(searchRequest.lon(), searchRequest.lat()));
        userLocation.setSRID(4326);
        NumberTemplate<Double> location = Expressions.numberTemplate(Double.class, "ST_Distance({0}, {1})", restaurantEntity.location, userLocation);
        BooleanExpression distanceExpression = location.loe(searchRequest.range() * 1000);

        JPAQuery<Restaurant> query = queryFactory.select(Projections.constructor(Restaurant.class
                        , restaurantEntity.name
                        , restaurantEntity.jibeonAddress
                        , restaurantEntity.roadAddress
                        , restaurantEntity.location
                        , restaurantEntity.category
                        , restaurantEntity.isBusinessActive
                        , restaurantEntity.score))
                .from(restaurantEntity)
                .where(restaurantEntity.location.isNotNull()
                        .and(restaurantEntity.isBusinessActive.isTrue())
                        .and(distanceExpression));

        if (searchRequest.sort() == RestaurantSort.RATING) {
            query = query.orderBy(restaurantEntity.score.desc());
        } else {
            query.orderBy(location.asc());}

        List<Restaurant> restaurants = query
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();

        long total = query.fetchCount();

        Page<Restaurant> page = new PageImpl<>(restaurants, pageRequest, total);

        return PageResponse.of(page);
    }

    public Optional<RestaurantDetail> selectRestaurantDetail(Long id) {
        return Optional.ofNullable(queryFactory.select(Projections.constructor(RestaurantDetail.class
                , restaurantEntity.id
                , restaurantEntity.name
                , restaurantEntity.jibeonAddress
                , restaurantEntity.roadAddress
                , restaurantEntity.location
                , restaurantEntity.category
                , restaurantEntity.telephone
                , restaurantEntity.score
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
