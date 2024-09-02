package com.yamyamnavi.domain.restaurant;

import com.yamyamnavi.api.v1.request.RestaurantSearchRequest;
import com.yamyamnavi.support.response.PageResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {
    PageResponse<Restaurant> selectRestaurants(RestaurantSearchRequest searchRequest, PageRequest pageRequest);
    RestaurantDetail find(Long id);
    RestaurantDetail update(Long id, RestaurantDetail restaurantDetail);

}
