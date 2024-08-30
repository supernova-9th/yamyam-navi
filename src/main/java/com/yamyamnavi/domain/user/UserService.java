package com.yamyamnavi.domain.user;

import com.yamyamnavi.api.v1.response.TokenResponse;

public interface UserService {

    User createUser(User user);

    TokenResponse signIn(String email, String password);
}