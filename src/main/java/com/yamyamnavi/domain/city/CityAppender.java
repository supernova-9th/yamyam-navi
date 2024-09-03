package com.yamyamnavi.domain.city;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CityAppender {

    private final CityConverter cityConverter;
    private final CityRepository cityRepository;

    public void append(String dosi, String sgg, double lon, double lat) {
        cityRepository.save(cityConverter.convertToCitySgg(dosi, sgg, lon, lat));
    }
}
