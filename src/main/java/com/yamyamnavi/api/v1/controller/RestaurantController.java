package com.yamyamnavi.api.v1.controller;

import com.yamyamnavi.api.v1.converter.RestaurantConverter;
import com.yamyamnavi.api.v1.response.RestaurantDetailResponse;
import com.yamyamnavi.domain.restaurant.RestaurantService;
import com.yamyamnavi.support.error.RestaurantNotFoundException;
import com.yamyamnavi.support.response.ResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/restaurants")
@Tag(name = "Restaurant API", description = "맛집 정보를 조회하는 API")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantConverter restaurantConverter;

    /**
     * 맛집 상세 정보를 조회합니다.
     *
     * @param id 조회할 맛집의 아이디
     * @return 조회된 맛집의 상세 정보가 담긴 RestaurantResponse 객체
     * @throws RestaurantNotFoundException 해당 맛집 정보를 찾을 수 없는 경우
     */
    @GetMapping("/{id}")
    @Operation(summary = "맛집 상세 조회", description = "맛집 상세 정보를 조회합니다.")
    public ResultResponse<RestaurantDetailResponse> getRestaurant(@PathVariable(value = "id") Long id) {
        return new ResultResponse<>(restaurantConverter.convertToRestaurantDetailResponse(restaurantService.getRestaurantDetail(id)));
    }
}
