package com.yamyamnavi.domain.review;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long id;
    private Long userId;
    private Long restaurantId;
    private String content;
    private Integer score;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Review(Long id, String content, Integer score, Long userId, Long restaurantId, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.content = content;
        this.score = score;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}