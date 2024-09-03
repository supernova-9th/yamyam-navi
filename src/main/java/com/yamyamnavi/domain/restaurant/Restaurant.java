package com.yamyamnavi.domain.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String jibeonAddress;
    private String roadAddress;
    private Location location;
    private String category;
    private double score;

    public Restaurant(Long id, String name, String jibeonAddress, String roadAddress, Point point, String category, double score) {
        this.id = id;
        this.name = name;
        this.jibeonAddress = jibeonAddress;
        this.roadAddress = roadAddress;
        this.location = new Location(point.getX(), point.getY());
        this.category = category;
        this.score = score;
    }
}
