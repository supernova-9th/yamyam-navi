package com.yamyamnavi.domain.city;

import com.yamyamnavi.api.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<CityResponse> getCites() {
        return cityFinder.findAll();
    }
}
