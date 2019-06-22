package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.City;
import com.example.AKTours.repository.CityRepository;
import com.example.AKTours.web.exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CityService {
    @Autowired
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> findAll() {
        log.info("Invoke CityRepisitory findAll ");
        List<City> collect = StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return collect;
    }

    public Optional<City> findCityByName(String name) throws EntityNotFoundException {
        log.info("Invoke CityRepository findCityByName using" + name);
        return Optional.of(cityRepository.findCityByName(name)
                .orElseThrow(() -> new EntityNotFoundException("No city found with name " + name)));
    }

    public List<City> findCityByCountryName(String name) throws EntityNotFoundException {
        log.info("Invoke CityRepository findCityByCoutryName using" + name);
        if (!cityRepository.findCitiesByCountryName(name).isEmpty()) {
            return cityRepository.findCitiesByCountryName(name);
        } else {
            throw new EntityNotFoundException("Not found any cities for country " + name);
        }
    }

    public List<City> findCityByContinentName(String name) throws EntityNotFoundException {
        log.info("Invoke CityRepository findCityByContinentName using" + name);
        if (!cityRepository.findCitiesByContinentName(name).isEmpty()) {
            return cityRepository.findCitiesByContinentName(name);
        } else {
            throw new EntityNotFoundException("Not found any cities for continent " + name);
        }
    }
}
