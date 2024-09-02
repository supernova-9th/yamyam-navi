package com.yamyamnavi.storage.restaurant;

import com.yamyamnavi.api.v1.request.RestaurantSearchRequest;
import com.yamyamnavi.domain.restaurant.Restaurant;
import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import com.yamyamnavi.domain.restaurant.RestaurantRepository;
import com.yamyamnavi.support.error.RestaurantNotFoundException;
import com.yamyamnavi.support.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantCoreRepository implements RestaurantRepository {
    private final RestaurantRepositoryImpl restaurantRepositoryImpl;

    @Override
    public PageResponse<Restaurant> selectRestaurants(RestaurantSearchRequest searchRequest, PageRequest pageRequest) {
        return restaurantRepositoryImpl.selectRestaurants(searchRequest, pageRequest);
    }

    public RestaurantDetail find(Long id) {
        return restaurantRepositoryImpl.selectRestaurantDetail(id).orElseThrow(RestaurantNotFoundException::new);
    }

    public RestaurantDetail update(Long id, RestaurantDetail restaurantDetail) {
        return restaurantRepositoryImpl.updateScore(id, restaurantDetail).orElseThrow(RestaurantNotFoundException::new);
    }
}
