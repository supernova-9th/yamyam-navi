package com.yamyamnavi.domain.user;

import com.yamyamnavi.support.error.UserRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;

    public void validateEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new UserRegistrationException();
        }
    }
}