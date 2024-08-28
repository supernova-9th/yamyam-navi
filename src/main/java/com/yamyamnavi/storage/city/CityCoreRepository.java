package com.yamyamnavi.storage.city;

import com.yamyamnavi.api.converter.CityConverter;
import com.yamyamnavi.domain.city.City;
import com.yamyamnavi.domain.city.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CityCoreRepository implements CityRepository {
    private final CityJpaRepository cityJpaRepository;
    private final CityConverter cityConverter;

    public List<City> findAll() {
        return cityConverter.convert(cityJpaRepository.findAll());
    }
}
