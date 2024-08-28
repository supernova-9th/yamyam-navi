package com.yamyamnavi.api.converter;

import com.yamyamnavi.domain.city.City;
import com.yamyamnavi.storage.city.CityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CityConverter {

    City convert(CityEntity resources);
    List<City> convert(List<CityEntity> resources);
}
