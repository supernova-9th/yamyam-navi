package com.yamyamnavi.domain.restaurant;

import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.support.response.PageResponse;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDetail {
    private Long id;
    private String name;
    private String jibeonAddress;
    private String roadAddress;
    private Location location;
    private String category;
    private Boolean isBusinessActive;
    private String telephone;
    private double score;
    private List<Review> reviews;
    private PageResponse<Review> pageReviews;

    public void updateReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    /**
     * 리뷰 목록 기반으로 평점을 업데이트하는 메서드.
     *
     * 리뷰 목록의 모든 리뷰의 평균 점수를 계산하여 소수점 첫째 자리에서 반올림해 설정합니다.
     *
     * @param reviews  기존 리뷰 목록
     */
    public void updateReviewsAndScore(List<Review> reviews) {
        double totalScore = reviews.stream().mapToDouble(Review::getScore).sum();
        this.score = Math.round((totalScore / reviews.size()) * 10) / 10.0;
    }

    public RestaurantDetail(Long id, String name, String jibeonAddress, String roadAddress, Point point, String category, Boolean isBusinessActive, String telephone, double score) {
        this.id = id;
        this.name = name;
        this.jibeonAddress = jibeonAddress;
        this.roadAddress = roadAddress;
        this.location = new Location(point.getX(), point.getY());
        this.category = category;
        this.isBusinessActive = isBusinessActive;
        this.telephone = telephone;
        this.score = score;
    }

    public void updateReviewsResponse(PageResponse<Review> reviews) {
        this.pageReviews = reviews;
    }
}
