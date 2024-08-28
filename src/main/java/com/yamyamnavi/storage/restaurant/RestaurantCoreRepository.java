package com.yamyamnavi.storage.restaurant;

import com.yamyamnavi.domain.restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantCoreRepository implements RestaurantRepository {
    private final RestaurantJpaRepository restaurantJpaRepository;
}
