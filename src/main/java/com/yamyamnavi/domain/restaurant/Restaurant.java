package com.yamyamnavi.domain.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private String name;
    private String jibeonAddress;
    private String roadAddress;
    private Location location;
    private String category;
    private Boolean isBusinessActive;
    private double score;

    public Restaurant(String name, String jibeonAddress, String roadAddress, Point point, String category, Boolean isBusinessActive, double score) {
        this.name = name;
        this.jibeonAddress = jibeonAddress;
        this.roadAddress = roadAddress;
        this.location = new Location(point.getX(), point.getY());
        this.category = category;
        this.isBusinessActive = isBusinessActive;
        this.score = score;
    }
}
