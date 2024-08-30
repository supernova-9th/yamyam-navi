package com.yamyamnavi.api.v1.response;

import com.yamyamnavi.domain.review.Review;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long id;

    private Long userId;
    private Long restaurantId;

    private String content;
    private Double score;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
