package com.yamyamnavi.domain.user;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.location.GeoPoint;
import com.yamyamnavi.domain.location.GeocodeService;
import com.yamyamnavi.domain.token.TokenIssuer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserAppender userAppender;
    private final UserLoginProcessor userLoginProcessor;
    private final UserValidator userValidator;
    private final UserFinder userFinder;
    private final TokenIssuer tokenIssuer;
    private final UserRepository userRepository;
    private final UserUpdater userUpdater;
    private final GeocodeService geocodeService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User createUser(User user, String address) {
        userValidator.validateEmail(user.getEmail());
        GeoPoint geoPoint = geocodeService.getGeoPointFromAddress(address);
        user.updateLocation(geoPoint.latitude(), geoPoint.longitude());
        return userAppender.append(user);
    }

    @Transactional
    public TokenResponse signIn(String email, String password) {
        return userLoginProcessor.process(email, password);
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return userFinder.findByEmailOrThrow(email);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userFinder.findByIdOrThrow(id);
    }

    @Transactional
    public void logout(String email) {
        tokenIssuer.revokeToken(email);
    }

    @Transactional
    public User changePassword(String email, String newPassword) {
        User user = userFinder.findByEmailOrThrow(email);
        String encodedPassword = passwordEncoder.encode(newPassword);
        return userUpdater.changePassword(user, encodedPassword);
    }

    @Transactional
    public User updateLocation(String email, String address) {
        User user = userFinder.findByEmailOrThrow(email);
        GeoPoint geoPoint = geocodeService.getGeoPointFromAddress(address);
        return userUpdater.updateLocation(user, geoPoint.latitude(), geoPoint.longitude());
    }
}