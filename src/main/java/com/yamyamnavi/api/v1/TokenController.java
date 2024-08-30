package com.yamyamnavi.api.v1;

import com.yamyamnavi.domain.user.TokenService;
import com.yamyamnavi.support.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping("/reissue")
    public ResultResponse<String> reissueToken(@RequestHeader("Authorization") String refreshToken) {
        String newAccessToken = tokenService.reissueAccessToken(refreshToken);
        return new ResultResponse<>(newAccessToken);
    }
}