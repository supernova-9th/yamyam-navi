package com.yamyamnavi.domain.restaurant;

import com.yamyamnavi.domain.review.Review;
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

    public void updateReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void updateReviewsAndScore(List<Review> reviews) {
        double averageScore = reviews.stream().mapToDouble(Review::getScore).average()    .orElse(0.0);
        this.score = Math.round(averageScore * 10) / 10.0;
        this.reviews = reviews;
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
}
