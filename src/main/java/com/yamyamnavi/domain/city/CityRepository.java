package com.yamyamnavi.domain.city;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository {
    List<CitySgg> findAll();

    void save(CitySgg citySgg);

    void removeAll();
}
