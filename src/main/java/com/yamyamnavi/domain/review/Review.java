package com.yamyamnavi.domain.review;

import lombok.*;

import java.time.LocalDate;

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
    private Double score;

    private LocalDate createdAt;
    private LocalDate updatedAt;
}
