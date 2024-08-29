package com.yamyamnavi.domain.city;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitySgg {
    private String city;
    private String sgg;
    private double longitude;
    private double latitude;
}
