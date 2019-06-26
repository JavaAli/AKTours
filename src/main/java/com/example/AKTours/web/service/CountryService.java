package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Country;
import com.example.AKTours.repository.CountryRepository;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        List<Country> collect = StreamSupport
                .stream(countryRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return collect;
    }

    public Country findByCountryName(String name) {
        log.info("Invoke CountryRepository findByCountryName using " + name);
        return countryRepository.findCountryByName(name);
    }

    public List<Country> findCountryByContinentName(String name) {
        log.info("Invoke CountryRepository findCountryByContinent_name using " + name);
        return StreamSupport
                .stream(countryRepository.findCountryByContinent_Name(name).spliterator(), false)
                .collect(Collectors.toList());
    }
}
