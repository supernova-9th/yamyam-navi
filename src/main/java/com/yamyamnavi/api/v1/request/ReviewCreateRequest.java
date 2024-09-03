package com.yamyamnavi.api.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "리뷰 생성 요청")
public class ReviewCreateRequest {

    @Schema(description = "가게 아이디", example = "100")
    @NotNull(message = "restaurantId는 null이 될 수 없습니다.")
    private Long restaurantId;

    @Schema(description = "리뷰 내용", example = "음식이 정말 맛있어요!")
    @NotBlank(message = "리뷰 내용을 작성해주세요.")
    @Size(max = 255, message = "리뷰 내용은 255자 이내로 작성해야 합니다.")
    private String content;

    @Schema(description = "리뷰 점수", example = "4")
    @NotNull(message = "리뷰 점수를 작성해주세요.")
    @Min(value = 0, message = "리뷰 점수는 최소 0점이어야 합니다.")
    @Max(value = 5, message = "리뷰 점수는 최대 5점이어야 합니다.")
    private Integer score;

}