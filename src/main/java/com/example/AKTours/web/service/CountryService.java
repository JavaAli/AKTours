package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Country;
import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.repository.CountryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Log4j2
@Service
public class CountryService {

    // @Autowired
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
        log.info("Invoke CountryRepository findByCounryName using " + name);

        return countryRepository.findCountryByName(name);
    }
}
