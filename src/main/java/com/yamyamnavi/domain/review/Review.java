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
}
