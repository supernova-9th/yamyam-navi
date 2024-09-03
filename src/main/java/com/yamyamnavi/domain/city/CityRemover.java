package com.yamyamnavi.domain.city;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityRemover {

    private final CityRepository cityRepository;


    public void removeAll() {
        cityRepository.removeAll();
    }
}
