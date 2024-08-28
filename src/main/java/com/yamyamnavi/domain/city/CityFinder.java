package com.yamyamnavi.domain.city;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CityFinder {

    private final CityRepository cityRepository;

    public Map<String, List<String>> findAll() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().collect(Collectors.groupingBy(
                        City::getDosi,
                        Collectors.mapping(City::getSgg, Collectors.toList())
                ));
    }
}
