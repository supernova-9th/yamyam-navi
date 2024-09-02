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
    private double latitude;
    private double longitude;

    /**
     * 사용자의 비밀번호를 변경합니다.
     * @param encryptedNewPassword 암호화된 새 비밀번호
     */
    public void changePassword(String encryptedNewPassword) {
        this.password = encryptedNewPassword;
    }

    public void activate() {
        this.active = true;
    }

    public void updateLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}