package com.yamyamnavi.api.v1.converter;

import com.yamyamnavi.api.v1.response.RestaurantDetailPageResponse;
import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {ReviewConverter.class})
public interface RestaurantConverter {

    @Mapping(target = "longitude", expression = "java(source.getLocation().getLongitude())")
    @Mapping(target = "latitude", expression = "java(source.getLocation().getLatitude())")
    RestaurantDetailPageResponse convertToRestaurantDetailPageResponse(RestaurantDetail source);

}