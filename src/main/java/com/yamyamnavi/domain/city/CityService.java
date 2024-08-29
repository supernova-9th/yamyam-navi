package com.yamyamnavi.domain.city;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityFinder cityFinder;

    /**
     * 도시 목록을 조회합니다.
     *
     * @return map key - 도/시
     *             value 시/군/구 리스트
     */
    @Transactional(readOnly = true)
    public List<City> getCites() {
        return cityFinder.findAll();
    }
}
