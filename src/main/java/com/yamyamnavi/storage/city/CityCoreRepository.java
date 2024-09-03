package com.yamyamnavi.storage.city;

import com.yamyamnavi.domain.city.CityConverter;
import com.yamyamnavi.domain.city.CityRepository;
import com.yamyamnavi.domain.city.CitySgg;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CityCoreRepository implements CityRepository {
    private final CityJpaRepository cityJpaRepository;
    private final CityConverter cityConverter;

    public List<CitySgg> findAll() {
        return cityConverter.convertToCitySgg(cityJpaRepository.findAll());
    }

    @Override
    public void save(CitySgg citySgg) {
        CityEntity entity = cityConverter.convertToCityEntity(citySgg);
        cityJpaRepository.save(entity);
    }
}
