package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.response.RestaurantResponse;
import com.yamyamnavi.api.v1.response.ReviewResponse;
import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import com.yamyamnavi.domain.review.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantConverter {

    @Mapping(target = "longitude", expression = "java(source.getLocation().getLongitude())")
    @Mapping(target = "latitude", expression = "java(source.getLocation().getLatitude())")
    RestaurantResponse convertToRestaurantResponse(RestaurantDetail source);

    ReviewResponse convertToReviewResponse(Review source);
}
