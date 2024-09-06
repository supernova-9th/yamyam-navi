package com.yamyamnavi.domain.token;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.user.UserRedisRepository;
import com.yamyamnavi.security.JwtProvider;
import com.yamyamnavi.support.error.JwtValidateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TokenIssuerTest {

    @Autowired
    private TokenIssuer tokenIssuer;

    @MockBean
    private JwtProvider jwtProvider;

    @MockBean
    private UserRedisRepository userRedisRepository;

    @Test
    public void 토큰_생성한다() {
        String email = "test@example.com";
        when(jwtProvider.createAccessToken(email)).thenReturn("access_token");
        when(jwtProvider.createRefreshToken(email)).thenReturn("refresh_token");

        TokenResponse response = tokenIssuer.createToken(email);

        assertNotNull(response);
        assertEquals("access_token", response.accessToken());
        assertEquals("refresh_token", response.refreshToken());
    }

    @Test
    public void 액세스_토큰_재발급한다() {
        String refreshToken = "refresh_token";
        String email = "test@example.com";
        when(jwtProvider.validateToken(refreshToken)).thenReturn(true);
        when(jwtProvider.getEmailFromToken(refreshToken)).thenReturn(email);
        when(userRedisRepository.getRefreshToken(email)).thenReturn(refreshToken);
        when(jwtProvider.createAccessToken(email)).thenReturn("new_access_token");

        TokenResponse response = tokenIssuer.reissueAccessToken(refreshToken);

        assertNotNull(response);
        assertEquals("new_access_token", response.accessToken());
    }

    @Test
    public void 유효하지_않은_리프레시_토큰으로_재발급시_예외_발생한다() {
        String invalidRefreshToken = "invalid_refresh_token";
        when(jwtProvider.validateToken(invalidRefreshToken)).thenReturn(false);

        assertThrows(JwtValidateException.class, () -> tokenIssuer.reissueAccessToken(invalidRefreshToken));
    }

    @Test
    public void 저장된_리프레시_토큰과_불일치시_예외_발생한다() {
        String refreshToken = "refresh_token";
        String email = "test@example.com";
        when(jwtProvider.validateToken(refreshToken)).thenReturn(true);
        when(jwtProvider.getEmailFromToken(refreshToken)).thenReturn(email);
        when(userRedisRepository.getRefreshToken(email)).thenReturn("different_refresh_token");

        assertThrows(JwtValidateException.class, () -> tokenIssuer.reissueAccessToken(refreshToken));
    }
}