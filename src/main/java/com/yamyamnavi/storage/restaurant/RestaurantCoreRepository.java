package com.yamyamnavi.storage.restaurant;

import com.yamyamnavi.domain.restaurant.RestaurantDetail;
import com.yamyamnavi.domain.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantCoreRepository implements RestaurantRepository {
    private final RestaurantJpaRepository restaurantJpaRepository;

    @Override
    public RestaurantDetail find(Long id) {
        return restaurantJpaRepository.selectRestaurantDetail(id);
    }
}
