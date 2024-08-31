package com.yamyamnavi.domain.restaurant;

import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository {
    RestaurantDetail find(Long id);
}
