package com.yamyamnavi.domain.restaurant;

import com.yamyamnavi.api.v1.request.RestaurantSearchRequest;
import com.yamyamnavi.support.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantFinder {

    private final RestaurantRepository restaurantRepository;

    public PageResponse<Restaurant> findRestaurants(RestaurantSearchRequest searchRequest, PageRequest pageRequest) {
        return restaurantRepository.selectRestaurants(searchRequest, pageRequest);
    }

    public RestaurantDetail find(Long id) {
        return restaurantRepository.find(id);
    }
}
