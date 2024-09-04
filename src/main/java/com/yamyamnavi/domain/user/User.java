package com.yamyamnavi.domain.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class User {

    private Long id;
    private String email;
    private String password;
    private Double latitude;
    private Double longitude;

    /**
     * 사용자의 비밀번호를 변경합니다.
     * @param encryptedNewPassword 암호화된 새 비밀번호
     */
    public void changePassword(String encryptedNewPassword) {
        this.password = encryptedNewPassword;
    }

    /**
     * 사용자의 주소를 변경합니다.
     * @param latitude 위도
     * @param longitude 경도
     */
    public void updateLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}