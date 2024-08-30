package com.yamyamnavi.storage.restaurant;

import com.yamyamnavi.domain.restaurant.RestaurantDetail;

public interface RestaurantRepositoryCustom {
    RestaurantDetail selectRestaurantDetail(Long id);
}
