package com.yamyamnavi.domain.user;

import com.yamyamnavi.api.v1.response.TokenResponse;
import com.yamyamnavi.domain.token.TokenIssuer;
import com.yamyamnavi.support.error.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserLoginProcessor {

    private final UserFinder userFinder;
    private final PasswordEncoder passwordEncoder;
    private final TokenIssuer tokenIssuer;

    public TokenResponse process(String email, String password) {
        User user = userFinder.findByEmailOrThrow(email);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException();
        }

        return tokenIssuer.createToken(user.getEmail());
    }
}