package com.yamyamnavi.api;

import com.yamyamnavi.domain.city.CityService;
import com.yamyamnavi.support.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/apis/cities")
public class CityController {

    private final CityService cityService;

    /**
     * 도시 목록을 조회합니다.
     *
     * @return map key - 도/시
     *             value 시/군/구 리스트
     */
    @GetMapping
    public ResultResponse<Map<String, List<String>>> getCites() {
        return new ResultResponse<>(cityService.getCites());
    }
}
