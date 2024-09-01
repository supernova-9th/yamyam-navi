package com.yamyamnavi.domain.review;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long id;
    private Long userId;
    private Long restaurantId;
    private String content;
    private Integer score;
    private LocalDateTime createdAt;

    public Review(Long userId, String content, Integer score, Long restaurantId, LocalDateTime createdAt) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.content = content;
        this.score = score;
        this.createdAt = createdAt;
    }

}