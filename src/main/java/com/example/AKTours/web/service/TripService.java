package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.repository.TripRepository;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
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
        public List<Trip> findTripByHotelName (String hotelName) throws EntityNotFoundException {
            log.info("Invoke TripRepository findHotelByStandard using " + hotelName);
            String hilton = "Hilton";
            if (hilton.equals(hotelName)) {
                return tripRepository.findTripByHotelName(hotelName);
//            List<Trip> tripsCollected = StreamSupport.stream(tripRepository.findTripByHotelName(hotelName).spliterator(),false)
//                    .collect(Collectors.toList());
//            return tripsCollected;
            } else {
                throw new EntityNotFoundException("Not found any trips for " + hotelName + "hotel");
            }
        }
    }
