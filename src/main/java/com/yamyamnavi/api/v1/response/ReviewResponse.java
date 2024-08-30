package com.yamyamnavi.api.v1.response;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        String content,
        Double score,
        Long userId,
        LocalDateTime createdAt
) {
}
