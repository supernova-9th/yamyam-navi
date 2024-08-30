package com.yamyamnavi.api.v1.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "리뷰 생성 요청")
public class ReviewCreateRequest {

    @Schema(description = "작성자 아이디", example = "1")
    @NotNull(message = "userId는 null이 될 수 없습니다.")
    private Long userId;

    @Schema(description = "가게 아이디", example = "100")
    @NotNull(message = "restaurantId는 null이 될 수 없습니다.")
    private Long restaurantId;

    @Schema(description = "리뷰 내용", example = "음식이 정말 맛있어요!")
    @NotBlank(message = "리뷰 내용을 작성해주세요.")
    private String content;

    @Schema(description = "리뷰 점수", example = "4.5")
    @NotNull(message = "리뷰 점수를 작성해주세요.")
    private Double score;

}
