package com.yamyamnavi.domain.user;

import com.yamyamnavi.support.error.UserRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    /**
     * 이메일의 유효성을 검사합니다. 이미 존재하는 이메일인 경우 예외를 발생시킵니다.
     *
     * @param email 검사할 이메일
     * @throws UserRegistrationException 이미 존재하는 이메일인 경우
     * @throws IllegalArgumentException 이메일이 null이거나 빈 문자열인 경우
     */
    public void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (userRepository.existsByEmail(email)) {
            throw new UserRegistrationException();
        }
    }
}