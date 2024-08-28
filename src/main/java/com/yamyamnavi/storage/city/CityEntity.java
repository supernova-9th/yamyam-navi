package com.yamyamnavi.storage.city;

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
@Table(name = "city")
public class CityEntity extends BaseEntity {

    @Column(nullable = false)
    private String dosi;

    @Column(nullable = false)
    private String sgg;

    @Column(columnDefinition = "POINT SRID 4326", nullable = false)
    private Point location;

    @Builder
    private CityEntity(String dosi, String sgg, Point location) {
        this.dosi = dosi;
        this.sgg = sgg;
        this.location = location;
    }

}
