package com.yamyamnavi.domain.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAppender {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAppender(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User append(User user) {
        user.changePassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}