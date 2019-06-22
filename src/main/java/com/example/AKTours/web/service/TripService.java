package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.repository.TripRepository;
import com.example.AKTours.web.exceptions.EntityNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TripService {
    @Autowired
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<Trip> findAllTrips() throws EntityNotFoundException {
        log.info("Invoke TripRepository findAllTrips");
        if (!tripRepository.findAll().isEmpty()) {
            List<Trip> tripsCollected = StreamSupport.stream(tripRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList());
            return tripsCollected;
        } else {
            throw new EntityNotFoundException("Trip repository is empty");
        }

    }

    public List<Trip> findTripByHotelName(String hotelName) throws EntityNotFoundException {
        log.info("Invoke TripRepository findTripByHotelName using " + hotelName);
        if (!tripRepository.findTripsByHotelName(hotelName).isEmpty()) {
            List<Trip> tripsCollected = StreamSupport
                    .stream(tripRepository.findTripsByHotelName(hotelName).spliterator(), false)
                    .collect(Collectors.toList());
            return tripsCollected;
        } else {
            throw new EntityNotFoundException("Not found any trips for hotel " + hotelName);
        }
    }

    public List<Trip> findTripByCityName(String cityName) throws EntityNotFoundException {
        log.info("Invoke TripRepository findTripByCityName using " + cityName);
        if (!tripRepository.findTripsByCityName(cityName).isEmpty()) {
            List<Trip> tripsCollected = StreamSupport
                    .stream(tripRepository.findTripsByCityName(cityName).spliterator(), false)
                    .collect(Collectors.toList());
            return tripsCollected;
        } else {
            throw new EntityNotFoundException("Not found any trips for city " + cityName);
        }
    }

    public List<Trip> findTripByCountryName(String name) throws EntityNotFoundException {
        log.info("Invoke TripRepository findTripByCountryName using " + name);
        if (!tripRepository.findTripsByCountryName(name).isEmpty()) {
            List<Trip> tripsCollected = StreamSupport
                    .stream(tripRepository.findTripsByCountryName(name).spliterator(), false)
                    .collect(Collectors.toList());
            return tripsCollected;
        } else {
            throw new EntityNotFoundException("Not found any trips for country " + name);
        }
    }

    public List<Trip> findTripByContinentName(String name) throws EntityNotFoundException {
        log.info("Invoke TripRepository findTripByContinentName using " + name);
        if (!tripRepository.findTripsByContinentName(name).isEmpty()) {
            List<Trip> tripsCollected = StreamSupport
                    .stream(tripRepository.findTripsByContinentName(name).spliterator(), false)
                    .collect(Collectors.toList());
            return tripsCollected;
        } else {
            throw new EntityNotFoundException("Not found any trips for continent " + name);
        }
    }

    public List<Trip> findTripByDate(LocalDate date) throws EntityNotFoundException {
        if (!tripRepository.findTripByDate(date).isEmpty()) {
            List<Trip> tripsCollected = StreamSupport
                    .stream(tripRepository.findTripByDate(date).spliterator(), false)
                    .collect(Collectors.toList());
            return tripsCollected;
        } else {
            throw new EntityNotFoundException("Not found any trips for date " + date);
        }
    }

    public List<Trip> findTripByPrice(BigDecimal price) throws EntityNotFoundException {
        if (!tripRepository.findAllByAdultPriceLessThanEqual(price).isEmpty()) {
            List<Trip> tripsCollected = StreamSupport
                    .stream(tripRepository.findAllByAdultPriceLessThanEqual(price).spliterator(), false)
                    .collect(Collectors.toList());
            return tripsCollected;
        }else {
            throw new EntityNotFoundException("Not found any trips for price " + price);
        }
    }
}