package com.yamyamnavi.domain.city;

import com.yamyamnavi.api.v1.response.SggResponse;
import com.yamyamnavi.storage.city.CityEntity;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CityConverter {

    @Mapping(target = "city", source = "dosi")
    CitySgg convertToCitySgg(String dosi, String sgg, double longitude, double latitude);

    @Mapping(target = "city", source = "dosi")
    @Mapping(target = "sgg", source = "sgg")
    @Mapping(target = "longitude", expression = "java(source.getLocation().getX())")
    @Mapping(target = "latitude", expression = "java(source.getLocation().getY())")
    CitySgg convertToCitySgg(CityEntity source);

    List<CitySgg> convertToCitySgg(List<CityEntity> source);

    @Mapping(target = "name", source = "sgg")
    SggResponse convertToSggResponse(CitySgg source);

    @Mapping(target = "location", source = "location")
    CityEntity convertToCityEntity(String dosi, String sgg, Point location);

    default CityEntity convertToCityEntity(CitySgg citySgg) {
        Point location = createPoint(citySgg.getLongitude(), citySgg.getLatitude());
        return convertToCityEntity(citySgg.getCity(), citySgg.getSgg(), location);
    }

    default Point createPoint(double lon, double lat) {
    return new GeometryFactory(new PrecisionModel(), 4326).createPoint(new Coordinate(lon, lat));
    }
}
