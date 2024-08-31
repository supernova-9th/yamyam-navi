package com.yamyamnavi.api.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TokenResponse(String accessToken, String refreshToken) {
    public TokenResponse(String accessToken) {
        this(accessToken, null);
    }
}