package com.yamyamnavi.security;

import com.yamyamnavi.support.error.JwtValidateException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {

    private JwtProvider jwtProvider;
    private final String SECRET_KEY = "testSecretKeyWithAtLeast256BitsForHS256Algorithm";
    private final long VALIDITY = 3600000; // 1 hour
    private final long REFRESH_VALIDITY = 86400000; // 24 hours

    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider();
        ReflectionTestUtils.setField(jwtProvider, "secretKey", SECRET_KEY);
        ReflectionTestUtils.setField(jwtProvider, "validityInMilliseconds", VALIDITY);
        ReflectionTestUtils.setField(jwtProvider, "refreshValidityInMilliseconds", REFRESH_VALIDITY);
    }

    @Test
    void createAccessToken_shouldCreateValidToken() {
        String email = "test@example.com";
        String token = jwtProvider.createAccessToken(email);

        assertNotNull(token);
        assertTrue(jwtProvider.validateToken(token));
        assertEquals(email, jwtProvider.getEmailFromToken(token));
    }

    @Test
    void createRefreshToken_shouldCreateValidToken() {
        String email = "test@example.com";
        String token = jwtProvider.createRefreshToken(email);

        assertNotNull(token);
        assertTrue(jwtProvider.validateToken(token));
        assertEquals(email, jwtProvider.getEmailFromToken(token));
    }

    @Test
    void validateToken_shouldReturnFalseForExpiredToken() {
        String email = "test@example.com";
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() - 1000); // Token that expired 1 second ago

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();

        assertThrows(JwtValidateException.class, () -> jwtProvider.validateToken(token));
    }
}