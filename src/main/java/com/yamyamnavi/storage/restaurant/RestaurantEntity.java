package com.yamyamnavi.storage.restaurant;

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
@Table(name = "restaurant")
public class RestaurantEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String jibeonAddress;

    @Column(nullable = false)
    private String roadAddress;

    @Column(columnDefinition = "POINT SRID 4326", nullable = false)
    private Point location;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Boolean isBusinessActive;

    @Column(nullable = false, unique = true, updatable = false)
    private String uniqueName;

    private String telephone;

    @Builder
    public RestaurantEntity(String name, String jibeonAddress, String roadAddress, Point location, String category, Boolean isBusinessActive, String uniqueName, String telephone) {
        this.name = name;
        this.jibeonAddress = jibeonAddress;
        this.roadAddress = roadAddress;
        this.location = location;
        this.category = category;
        this.isBusinessActive = isBusinessActive;
        this.uniqueName = uniqueName;
        this.telephone = telephone;
    }
}
