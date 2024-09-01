package com.yamyamnavi.storage.restaurant;

import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import com.yamyamnavi.domain.restaurant.RestaurantRepository;
import com.yamyamnavi.support.error.RestaurantNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantCoreRepository implements RestaurantRepository {
    private final RestaurantRepositoryImpl restaurantRepositoryImpl;

    public RestaurantDetail find(Long id) {
        return restaurantRepositoryImpl.selectRestaurantDetail(id).orElseThrow(RestaurantNotFoundException::new);
    }

    @Override
    public RestaurantDetail update(Long id, RestaurantDetail restaurantDetail) {
        return restaurantRepositoryImpl.updateReview(id, restaurantDetail).orElseThrow(RestaurantNotFoundException::new);
    }
}
