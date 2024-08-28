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

    @GetMapping
    public ResultResponse<Map<String, List<String>>> getCites() {
        return new ResultResponse<>(cityService.getCites());
    }
}
