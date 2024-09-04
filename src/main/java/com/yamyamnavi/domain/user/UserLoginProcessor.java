package com.yamyamnavi.domain.user;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.token.TokenIssuer;
import com.yamyamnavi.support.error.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserLoginProcessor {

    private final UserFinder userFinder;
    private final PasswordEncoder passwordEncoder;
    private final TokenIssuer tokenIssuer;

    /**
     * 사용자 로그인을 처리합니다.
     *
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 로그인 성공 시 발급되는 토큰 정보
     * @throws InvalidPasswordException 비밀번호가 일치하지 않는 경우
     */
    public TokenResponse process(String email, String password) {
        log.info("Attempting login for email: {}", email);
        User user = userFinder.findByEmailOrThrow(email);
        log.info("User found: {}", user.getId());

        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.warn("Invalid password for user: {}", email);
            throw new InvalidPasswordException();
        }

        log.info("Password matched for user: {}", email);
        return tokenIssuer.createToken(user.getEmail());
    }
}