package com.yamyamnavi.domain.user;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.token.TokenIssuer;
import lombok.RequiredArgsConstructor;
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

    @Transactional
    public User createUser(User user) {
        userValidator.validateEmail(user.getEmail());
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

    @Transactional
    public void logout(String email) {
        tokenIssuer.revokeToken(email);
    }
}