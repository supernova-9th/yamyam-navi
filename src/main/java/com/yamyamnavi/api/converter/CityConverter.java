package com.yamyamnavi.api.converter;

import com.yamyamnavi.api.response.SggResponse;
import com.yamyamnavi.domain.city.CitySgg;
import com.yamyamnavi.storage.city.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityConverter {

    @Mapping(target = "city", source = "dosi")
    @Mapping(target = "sgg", source = "sgg")
    @Mapping(target = "longitude", expression = "java(source.getLocation().getX())")
    @Mapping(target = "latitude", expression = "java(source.getLocation().getY())")
    CitySgg convertToCitySgg(CityEntity source);

    List<CitySgg> convertToCitySgg(List<CityEntity> source);

    @Mapping(target = "name", source = "sgg")
    SggResponse convertToSggResponse(CitySgg source);
}
