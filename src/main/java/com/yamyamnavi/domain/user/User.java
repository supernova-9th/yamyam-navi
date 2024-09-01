package com.yamyamnavi.domain.user;

import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

@Getter
@Builder
public class User {

    private Long id;
    private String email;
    private String password;
    private boolean active;
    private Point location;

    public void setPassword(String password) {
        this.password = password;
    }

    public void activate() {
        this.active = true;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}