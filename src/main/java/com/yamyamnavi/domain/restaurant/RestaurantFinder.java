package com.yamyamnavi.domain.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantFinder {

    private final RestaurantRepository restaurantRepository;

    public RestaurantDetail find(Long id) {
        return restaurantRepository.find(id);
    }
}
