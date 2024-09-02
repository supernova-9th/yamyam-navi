package com.yamyamnavi.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUpdater {
    private final UserRepository userRepository;

    public User changePassword(User user, String encryptedNewPassword) {
        user.changePassword(encryptedNewPassword);
        return userRepository.update(user);
    }

    public User updateLocation(User user, double latitude, double longitude) {
        user.updateLocation(latitude, longitude);
        return userRepository.update(user);
    }
}