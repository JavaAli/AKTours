package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Airport;
import com.example.AKTours.repository.AirportRepository;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class AirportService {

    @Autowired
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {

        this.airportRepository = airportRepository;
    }

    public List<Airport> findAll() {
        log.info("Invoke airport repisitory findAll ");
        List<Airport> collect = StreamSupport.stream(airportRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return collect;
    }

    public Airport findAirportByName(String name) throws EntityNotFoundException {
        log.info("Invoke airport repository fingAirportByName using " + name);
        Airport airport=airportRepository.findAirportByName(name)
                .orElseThrow(()->new EntityNotFoundException("Airport with name "+name+"not exist in base"));
        return airport;
    }

    public List<Airport> findAirportByCityName(String name) {
        log.info("Invoke airport repository find airports by city using " + name);

        String paris = "Paris";
        String katowice = "Katowice";
        String londyn = "London";

        if (londyn.equals(name)) {
            return airportRepository.findAirportsInLondon(name);
        } else if (paris.equals(name)) {
            return airportRepository.findAirportsInParis(name);
        } else if (katowice.equals(name)) {
            return airportRepository.findAirportsInKatowice(name);
        } else {
            return null;
        }

    }

}
