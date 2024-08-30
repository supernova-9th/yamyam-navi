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
    private Point location;
    private String category;
    private Boolean isBusinessActive;
    private String telephone;
    private double score;
    private List<Review> reviews;

    public void updateReviewsAndScore(List<Review> reviews) {
        double averageScore = reviews.stream().mapToDouble(Review::getScore).average()    .orElse(0.0);
        this.score = Math.round(averageScore * 10) / 10.0;
        this.reviews = reviews;
    }
}
