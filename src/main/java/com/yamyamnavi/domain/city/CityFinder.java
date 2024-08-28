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

    /**
     * 모든 도시를 조회하고, 도시에 따라 그룹화하여 반환합니다.
     *
     * 이 메서드는 데이터베이스에서 모든 도시 정보를 조회한 후,
     * 도시(`dosi`)를 기준으로 그룹화하고, 각 그룹에 속하는 시군구(`sgg`) 목록을 맵 형태로 반환합니다.
     *
     * @return 키는 도/시(`dosi`), 값은 해당 도시의 시/군/구(`sgg`) 리스트입니다.
     */
    public Map<String, List<String>> findAll() {
        List<City> cities = cityRepository.findAll();
        return cities.stream().collect(Collectors.groupingBy(
                        City::getDosi,
                        Collectors.mapping(City::getSgg, Collectors.toList())
                ));
    }
}
