package com.yamyamnavi.domain.restaurant;

import com.yamyamnavi.api.v1.request.RestaurantSearchRequest;
import com.yamyamnavi.domain.review.ReviewFinder;
import com.yamyamnavi.support.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantFinder restaurantFinder;
    private final ReviewFinder reviewFinder;

    /**
     * 맛집의 상세 정보를 조회합니다.
     *
     * @param id 조회할 맛집 아이디
     * @param pageRequest 페이지 요청 정보를 담고 있는 PageRequest 객체
     * @return 조회된 맛집의 상세 정보가 담긴 RestaurantDetail 객체
     */
    @Transactional(readOnly = true)
    public RestaurantDetail getRestaurantDetail(Long id, PageRequest pageRequest) {
        RestaurantDetail detail = restaurantFinder.find(id);
        detail.updateReviewsResponse(reviewFinder.findReviews(id, pageRequest));
        return detail;
    }

    /**
     * 맛집 목록을 조회합니다.
     *
     * @param searchRequest 맛집 검색 조건을 담고 있는 RestaurantSearchRequest 객체
     * @param pageRequest 페이지 요청 정보를 담고 있는 PageRequest 객체
     * @return 검색 조건에 맞는 맛집 목록이 담긴 PageResponse 객체
     */
    @Transactional(readOnly = true)
    public PageResponse<Restaurant> getRestaurants(RestaurantSearchRequest searchRequest, PageRequest pageRequest) {
        return restaurantFinder.findRestaurants(searchRequest, pageRequest);
    }
}
