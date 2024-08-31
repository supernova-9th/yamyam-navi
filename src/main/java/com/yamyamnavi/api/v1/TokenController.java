package com.yamyamnavi.api.v1;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.token.TokenIssuer;
import com.yamyamnavi.support.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenIssuer tokenIssuer;

    @PostMapping("/reissue")
    public ResultResponse<TokenResponse> reissueToken(@RequestHeader("Authorization") String authHeader) {
        String refreshToken = authHeader.replace("Bearer ", "");
        TokenResponse tokenResponse = tokenIssuer.reissueAccessToken(refreshToken);
        return new ResultResponse<>(tokenResponse);
    }
}