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
public class RestaurantDetail {
    private Long id;
    private String name;
    private String jibeonAddress;
    private String roadAddress;
    private Point location;
    private String category;
    private Boolean isBusinessActive;
    private String telephone;
}
