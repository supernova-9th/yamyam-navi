package com.yamyamnavi.storage.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJpaRepository extends JpaRepository<RestaurantEntity, Long>, RestaurantRepositoryCustom {
}