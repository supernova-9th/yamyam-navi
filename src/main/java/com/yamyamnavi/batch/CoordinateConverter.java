package com.yamyamnavi.batch;

import com.yamyamnavi.support.utils.GeometryUtils;
import jakarta.annotation.PostConstruct;
import org.locationtech.jts.geom.Point;
import org.locationtech.proj4j.*;
import org.springframework.stereotype.Component;

@Component
public class CoordinateConverter {

    private CoordinateTransform transform;

    @PostConstruct
    public void init() {
        CRSFactory crsFactory = new CRSFactory();
        CoordinateReferenceSystem sourceCRS = crsFactory.createFromParameters("EPSG:2097", "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=500000 +ellps=GRS80 +units=m +no_defs");
        CoordinateReferenceSystem targetCRS = crsFactory.createFromParameters("EPSG:4326", "+proj=longlat +datum=WGS84 +no_defs");
        
        CoordinateTransformFactory ctFactory = new CoordinateTransformFactory();
        transform = ctFactory.createTransform(sourceCRS, targetCRS);
    }

    public Point convert(double x, double y) {
        ProjCoordinate sourceCoord = new ProjCoordinate(x, y);
        ProjCoordinate targetCoord = new ProjCoordinate();
        transform.transform(sourceCoord, targetCoord);
        return GeometryUtils.getPoint(targetCoord.x, targetCoord.y);
    }
}