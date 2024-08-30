package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.response.RestaurantResponse;
import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantConverter {

    @Mapping(target = "longitude", expression = "java(source.getLocation().getX())")
    @Mapping(target = "latitude", expression = "java(source.getLocation().getY())")
    @Mapping(target = "score", ignore = true)
    RestaurantResponse convertToRestaurantResponse(RestaurantDetail source);
}
