package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.City;
import com.example.AKTours.repository.CityRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAll() {
        log.info("Invoke city repisitory findAll ");
        List<City> collect = StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return collect;

    }

    public City findCityByName(String name) {
        log.info("Invoke city repository using" + name);
        return cityRepository.findCityByName(name);
    }
}
