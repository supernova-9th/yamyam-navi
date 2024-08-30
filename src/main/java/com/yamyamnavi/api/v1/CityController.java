package com.yamyamnavi.api.v1;

import com.yamyamnavi.api.v1.response.CityResponse;
import com.yamyamnavi.domain.city.CityService;
import com.yamyamnavi.support.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/apis/cities")
@Tag(name = "City API", description = "도시 정보를 조회하는 API")
public class CityController {

    private final CityService cityService;

    /**
     * 도시 목록을 조회합니다.
     *
     * @return map key - 도/시
     *             value 시/군/구 리스트
     */
    @GetMapping
    @Operation(summary = "도시 목록 조회", description = "도시 및 시/군/구의 리스트를 조회합니다.")
    public ResultResponse<List<CityResponse>> getCites() {
        return new ResultResponse<>(cityService.getCites());
    }
}
