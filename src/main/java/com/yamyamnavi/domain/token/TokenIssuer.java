package com.yamyamnavi.domain.token;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.user.User;
import com.yamyamnavi.domain.user.UserFinder;
import com.yamyamnavi.security.JwtProvider;
import com.yamyamnavi.domain.user.UserRedisRepository;
import com.yamyamnavi.support.error.JwtValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class TokenIssuer {

    private final JwtProvider jwtProvider;
    private final UserFinder userFinder;
    private final UserRedisRepository userRedisRepository;

    public TokenIssuer(JwtProvider jwtProvider, UserFinder userFinder, UserRedisRepository userRedisRepository) {
        this.jwtProvider = jwtProvider;
        this.userFinder = userFinder;
        this.userRedisRepository = userRedisRepository;
    }

    @Transactional
    public TokenResponse createToken(String email) {
        log.info("Creating tokens for user: {}", email);
        String accessToken = jwtProvider.createAccessToken(email);
        String refreshToken = jwtProvider.createRefreshToken(email);

        log.info("Storing refresh token in Redis for user: {}", email);
        userRedisRepository.setRefreshToken(email, refreshToken, jwtProvider.getRefreshTokenValidityInMilliseconds());

        return new TokenResponse(accessToken, refreshToken);
    }

    @Transactional
    public TokenResponse reissueAccessToken(String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new JwtValidateException();
        }

        String email = jwtProvider.getEmailFromToken(refreshToken);
        String storedRefreshToken = userRedisRepository.getRefreshToken(email);

        if (storedRefreshToken == null) {
            throw new JwtValidateException();
        }

        if (!refreshToken.equals(storedRefreshToken)) {
            throw new JwtValidateException();
        }

        String newAccessToken = jwtProvider.createAccessToken(email);
        return new TokenResponse(newAccessToken);
    }

    @Transactional
    public void revokeToken(String email) {
        userRedisRepository.deleteRefreshToken(email);
    }
}