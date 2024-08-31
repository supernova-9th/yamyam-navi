package com.yamyamnavi.storage.user;

import com.yamyamnavi.storage.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean active;

//    @Column(columnDefinition = "POINT SRID 4326", nullable = false)
//    private Point location;

    public UserEntity(String email, String password) {
        this.email = email;
        this.password = password;
        this.active = false;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void activate() {
        this.active = true;
    }
}