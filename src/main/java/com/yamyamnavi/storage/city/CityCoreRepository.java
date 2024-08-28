package com.yamyamnavi.storage.city;

import com.yamyamnavi.domain.city.CityRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CityCoreRepository implements CityRepository {
    private final CityJpaRepository cityJpaRepository;
}
