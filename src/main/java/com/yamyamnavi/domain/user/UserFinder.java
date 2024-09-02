package com.yamyamnavi.domain.user;

import com.yamyamnavi.support.error.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFinder {

    private final UserRepository userRepository;

    /**
     * 이메일로 사용자를 찾습니다. 사용자가 없으면 예외를 발생시킵니다.
     *
     * @param email 찾고자 하는 사용자의 이메일
     * @return 찾은 사용자 객체
     * @throws UserNotFoundException 사용자를 찾지 못한 경우
     */
    public User findByEmailOrThrow(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }

    public User findByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}