package com.yamyamnavi.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private Long id;
    private String email;
    private String password;
    private boolean active;
    private String refreshToken;

    public void setPassword(String password) {
        this.password = password;
    }

    public void activate() {
        this.active = true;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}