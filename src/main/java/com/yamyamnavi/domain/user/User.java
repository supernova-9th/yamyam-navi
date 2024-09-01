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

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    public void activate() {
        this.active = true;
    }

    public void updateLocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}