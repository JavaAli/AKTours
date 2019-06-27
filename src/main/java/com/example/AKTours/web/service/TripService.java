package com.example.AKTours.web.service;

import com.example.AKTours.model.dtos.TripDto;
import com.example.AKTours.model.entity.Airport;
import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.repository.AirportRepository;
import com.example.AKTours.repository.HotelRepository;
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
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class TripService {
    @Autowired
    private final TripRepository tripRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private AirportRepository airportRepository;

    public TripService(TripRepository tripRepository, AirportRepository airportRepository, HotelRepository hotelRepository) {
        this.tripRepository = tripRepository;
        this.airportRepository = airportRepository;
        this.hotelRepository = hotelRepository;
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
        log.info("Invoke CityRepository findTripByDate using " + date.toString());
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
        log.info("Invoke CityRepository findTripByPrice using " + price);
        if (!tripRepository.findAllByAdultPriceLessThanEqual(price).isEmpty()) {
            List<Trip> tripsCollected = StreamSupport
                    .stream(tripRepository.findAllByAdultPriceLessThanEqual(price).spliterator(), false)
                    .collect(Collectors.toList());
            return tripsCollected;
        } else {
            throw new EntityNotFoundException("Not found any trips for price " + price);
        }
    }

    @Transactional
    public Trip addTrip(TripDto tripDto) throws EntityNotFoundException {
        log.info("Invoke convertToTrip method");
        Trip tripToSave = convertTripDtoToTrip(tripDto);
        Hotel hotel = findByHotelName(tripDto.getHotel());
        Airport homeAirportToSave = findAirportByName(tripDto.getHomeAirport());
        Airport destAirportToSave = findAirportByName(tripDto.getDestinAirport());
        log.info("add to hotel trip " + tripDto.toString());
        hotel.addTripToHotels(tripToSave);
        log.info("add trip to home Airport");
        homeAirportToSave.addTripToTripsHome(tripToSave);
        log.info("add trip to destination Airport");
        destAirportToSave.addTripToTripsDest(tripToSave);
        log.info("Invoke CityRepository save new trip");
        tripRepository.save(tripToSave);
        log.info("New trip saved in repository");
        return tripToSave;
    }

    public Trip convertTripDtoToTrip(TripDto tripDto) {
        Trip trip = Trip.builder()
                .numberOfDays(tripDto.getNumberOfDays())
                .ReturnDate(tripDto.getReturnDate())
                .DepartureDate(tripDto.getDepartureDate())
                .adultPrice(BigDecimal.valueOf(tripDto.getAdultPrice()))
                .childrenPrice(BigDecimal.valueOf(tripDto.getChildrenPrice()))
                .promoPrice(BigDecimal.valueOf(tripDto.getPromoPrice()))
                .adultVacancy(tripDto.getAdultVacancy())
                .childrenVacancy(tripDto.getChildrenVacancy())
                .boardType(tripDto.getBoardType())
                .build();
        return trip;
    }

    public Hotel findByHotelName(String name) throws EntityNotFoundException {
        log.info("Invoke hotel repository findByHotelName using " + name);
        return hotelRepository.findHotelByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with name " + name + "not exist in base"));
    }

    public Airport findAirportByName(String name) throws EntityNotFoundException {
        log.info("Invoke airport repository fingAirportByName using " + name);
        return airportRepository.findAirportByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Airport with name " + name + "not exist in base"));
    }
}