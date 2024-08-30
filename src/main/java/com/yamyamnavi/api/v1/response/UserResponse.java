package com.yamyamnavi.api.v1.response;

public record UserResponse(
        Long id,
        String email,
        boolean active
) {}