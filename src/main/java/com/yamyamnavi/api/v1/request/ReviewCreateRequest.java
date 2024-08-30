package com.yamyamnavi.api.v1.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {

    @NotNull(message = "userId는 null이 될 수 없습니다.")
    private Long userId;

    @NotNull(message = "restaurantId는 null이 될 수 없습니다.")
    private Long restaurantId;

    @NotBlank(message = "리뷰 내용을 작성해주세요.")
    private String content;

    @NotBlank(message = "리뷰 점수를 작성해주세요.")
    private Double score;

}
