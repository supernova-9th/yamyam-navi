package com.yamyamnavi.api.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yamyamnavi.api.v1.converter.ReviewConverter;
import com.yamyamnavi.api.v1.request.ReviewCreateRequest;
import com.yamyamnavi.api.v1.response.ReviewResponse;
import com.yamyamnavi.config.SecurityConfig;
import com.yamyamnavi.domain.review.Review;
import com.yamyamnavi.domain.review.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReviewController.class)
@Import(SecurityConfig.class)
class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ReviewService reviewService;
    @MockBean
    private ReviewConverter reviewConverter;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 리뷰_생성_API() throws Exception {
        // given
        ReviewCreateRequest request = new ReviewCreateRequest(1L, 1L, "goood", 4);
        Review review  = new Review(1L, 1L, 1L, "goood", 4, LocalDateTime.now(), LocalDateTime.now());
        Review savedReview  = new Review(1L, 1L, 1L, "goood", 4, LocalDateTime.now(), LocalDateTime.now());
        ReviewResponse response = new ReviewResponse(1L, "goood", 4, 1L, 1L, LocalDateTime.now());

        given(reviewConverter.convertToReview(request)).willReturn(review);
        given(reviewService.createReview(review)).willReturn(savedReview);
        given(reviewConverter.convertToReviewResponse(savedReview)).willReturn(response);

        // when & then
        mockMvc.perform(post("/api/v1/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("success"))
                .andExpect(jsonPath("$.result.id").value(1L))
                .andExpect(jsonPath("$.result.userId").value(1L))
                .andExpect(jsonPath("$.result.restaurantId").value(1L))
                .andExpect(jsonPath("$.result.content").value("goood"))
                .andExpect(jsonPath("$.result.score").value(4));
    }

}