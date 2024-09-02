package com.yamyamnavi.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class UserRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 사용자의 리프레시 토큰을 Redis에 저장합니다.
     *
     * @param email 사용자 이메일
     * @param refreshToken 리프레시 토큰
     * @param ttl 토큰의 유효 기간 (밀리초)
     */
    public void setRefreshToken(String email, String refreshToken, long ttl) {
        redisTemplate.opsForValue().set(email, refreshToken, ttl, TimeUnit.MILLISECONDS);
    }

    /**
     * 사용자의 리프레시 토큰을 Redis에서 조회합니다.
     *
     * @param email 사용자 이메일
     * @return 저장된 리프레시 토큰
     */
    public String getRefreshToken(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    /**
     * 사용자의 리프레시 토큰을 Redis에서 삭제합니다.
     *
     * @param email 사용자 이메일
     */
    public void deleteRefreshToken(String email) {
        redisTemplate.delete(email);
    }
}