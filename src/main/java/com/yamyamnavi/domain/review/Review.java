package com.yamyamnavi.domain.review;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long id;
    private String content;
    private Double score;
    private Long userId;
    private LocalDateTime createdAt;
}
