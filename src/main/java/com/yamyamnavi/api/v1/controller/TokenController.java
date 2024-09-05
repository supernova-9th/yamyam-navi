package com.yamyamnavi.api.v1.controller;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.token.TokenIssuer;
import com.yamyamnavi.support.error.JwtValidateException;
import com.yamyamnavi.support.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenIssuer tokenIssuer;

    /**
     * 토큰을 재발급합니다.
     *
     * @param authHeader 리프레시 토큰이 포함된 Authorization 헤더.
     * @return TokenResponse 새로운 액세스 토큰과 리프레시 토큰을 포함하는 객체.
     * @throws JwtValidateException 토큰이 만료되었거나 유효하지 않은 경우
     */
    @PostMapping("/reissue")
    public ResultResponse<TokenResponse> reissueToken(@RequestHeader("Authorization") String authHeader) {
        String refreshToken = authHeader.replace("Bearer ", "");
        TokenResponse tokenResponse = tokenIssuer.reissueAccessToken(refreshToken);
        return new ResultResponse<>(tokenResponse);
    }
}