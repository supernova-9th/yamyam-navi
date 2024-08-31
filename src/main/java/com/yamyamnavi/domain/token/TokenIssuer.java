package com.yamyamnavi.domain.token;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.domain.user.UserFinder;
import com.yamyamnavi.security.JwtProvider;
import com.yamyamnavi.domain.user.UserRedisRepository;
import com.yamyamnavi.support.error.JwtValidateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TokenIssuer {

    private final JwtProvider jwtProvider;
    private final UserFinder userFinder;
    private final UserRedisRepository userRedisRepository;

    @Transactional
    public TokenResponse createToken(String email) {
        String accessToken = jwtProvider.createAccessToken(email);
        String refreshToken = jwtProvider.createRefreshToken(email);

        User user = userFinder.findByEmailOrThrow(email);
        userRedisRepository.setRefreshToken(user.getId().toString(), refreshToken, jwtProvider.getRefreshTokenValidityInMilliseconds());

        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public TokenResponse reissueAccessToken(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new JwtValidateException();
        }

        String email = jwtProvider.getEmailFromToken(refreshToken);
        User user = userFinder.findByEmailOrThrow(email);

        String storedRefreshToken = userRedisRepository.getRefreshToken(user.getId().toString());
        if (!refreshToken.equals(storedRefreshToken)) {
            throw new JwtValidateException();
        }

        String newAccessToken = jwtProvider.createAccessToken(email);
        return new TokenResponse(newAccessToken);
    }

    @Transactional
    public void revokeToken(String email) {
        User user = userFinder.findByEmailOrThrow(email);
        userRedisRepository.deleteRefreshToken(user.getId().toString());
    }
}