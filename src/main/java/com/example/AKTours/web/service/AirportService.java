package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Airport;
import com.example.AKTours.repository.AirportRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import sun.plugin2.message.Message;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class AirportService {

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

    public Airport findAirportByName(String name) {
        log.info("Invoke airport repository using" + name);
        return airportRepository.findAirportByName(name);
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
