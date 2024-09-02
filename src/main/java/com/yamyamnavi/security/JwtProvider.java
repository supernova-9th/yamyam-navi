package com.yamyamnavi.security;

import com.yamyamnavi.support.error.JwtValidateException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length}")
    private long validityInMilliseconds;

    @Value("${security.jwt.token.refresh-expire-length}")
    private long refreshValidityInMilliseconds;

    /**
     * JWT 서명을 위한 키를 생성합니다.
     *
     * @return 서명 키
     */
    private Key getSigningKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 액세스 토큰을 생성합니다.
     *
     * @param email 사용자 이메일
     * @return 생성된 액세스 토큰
     */
    public String createAccessToken(String email) {
        return createToken(email, validityInMilliseconds);
    }

    /**
     * 리프레시 토큰을 생성합니다.
     *
     * @param email 사용자 이메일
     * @return 생성된 리프레시 토큰
     */
    public String createRefreshToken(String email) {
        return createToken(email, refreshValidityInMilliseconds);
    }

    /**
     * JWT 토큰을 생성합니다.
     *
     * @param email 사용자 이메일
     * @param validityPeriod 토큰의 유효 기간
     * @return 생성된 JWT 토큰
     */
    private String createToken(String email, long validityPeriod) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityPeriod);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 토큰에서 이메일을 추출합니다.
     *
     * @param token JWT 토큰
     * @return 토큰에서 추출한 이메일
     */
    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * 토큰의 유효성을 검증합니다.
     *
     * @param token 검증할 JWT 토큰
     * @return 토큰의 유효성 여부
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtValidateException();
        }
    }

    /**
     * 리프레시 토큰의 유효 기간을 반환합니다.
     *
     * @return 리프레시 토큰의 유효 기간 (밀리초)
     */
    public long getRefreshTokenValidityInMilliseconds() {
        return refreshValidityInMilliseconds;
    }
}