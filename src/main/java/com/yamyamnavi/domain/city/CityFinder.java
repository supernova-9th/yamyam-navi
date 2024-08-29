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
    public List<City> findAll() {
        List<CitySgg> citySggList = cityRepository.findAll();

        Map<String, List<CitySgg>> citySggMap = citySggList.stream().collect(Collectors.groupingBy(CitySgg::getCity));

        List<City> cities = citySggMap.entrySet().stream()
                .map(entry -> {
                    String cityName = entry.getKey();
                    List<Sgg> sggList = entry.getValue().stream()
                            .map(CitySgg::createSgg)
                            .collect(Collectors.toList());
                    return new City(cityName, sggList);
                })
                .collect(Collectors.toList());

        return cities;
    }
}
