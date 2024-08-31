package com.yamyamnavi.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class UserRedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public void setRefreshToken(String userId, String refreshToken, long ttl) {
        redisTemplate.opsForValue().set(userId, refreshToken, ttl, TimeUnit.MILLISECONDS);
    }

    public String getRefreshToken(String userId) {
        return redisTemplate.opsForValue().get(userId);
    }

    public void deleteRefreshToken(String userId) {
        redisTemplate.delete(userId);
    }
}