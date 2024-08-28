package com.yamyamnavi.domain.city;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityFinder cityFinder;

    @Transactional(readOnly = true)
    public Map<String, List<String>> getCites() {
        return cityFinder.findAll();
    }
}
