package com.yamyamnavi.domain.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantFinder restaurantFinder;

    @Transactional(readOnly = true)
    public RestaurantDetail getRestaurantDetail(Long id) {
        return restaurantFinder.find(id);
    }

}
