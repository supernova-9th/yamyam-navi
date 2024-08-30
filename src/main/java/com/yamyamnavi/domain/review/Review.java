package com.yamyamnavi.domain.review;

import lombok.*;

import java.time.LocalDate;
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
    private Double score;

    private LocalDateTime createdAt;


}
