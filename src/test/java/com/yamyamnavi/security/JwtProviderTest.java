package com.yamyamnavi.security;

import com.yamyamnavi.support.error.JwtValidateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtProviderTest {

    @Autowired
    private JwtProvider jwtProvider;

    @Test
    public void 액세스_토큰_생성한다() {
        String email = "test@example.com";
        String token = jwtProvider.createAccessToken(email);

        assertNotNull(token);
        assertTrue(jwtProvider.validateToken(token));
        assertEquals(email, jwtProvider.getEmailFromToken(token));
    }

    @Test
    public void 리프레시_토큰_생성한다() {
        String email = "test@example.com";
        String token = jwtProvider.createRefreshToken(email);

        assertNotNull(token);
        assertTrue(jwtProvider.validateToken(token));
        assertEquals(email, jwtProvider.getEmailFromToken(token));
    }

    @Test
    public void 유효하지_않은_토큰_검증시_예외_발생한다() {
        String invalidToken = "invalid.token.string";
        assertThrows(JwtValidateException.class, () -> jwtProvider.validateToken(invalidToken));
    }
}