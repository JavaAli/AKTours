package com.example.AKTours.web.service;

import com.example.AKTours.model.dto.TripDTO;
import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.repository.TripRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class TripService {
    @Autowired
    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<TripDTO> findAllTrips(){
        return StreamSupport.stream(tripRepository.findAll().spliterator(), false)
                .map(this::convertTripToDto)
                .collect(Collectors.toList());
    }

    public TripDTO convertTripToDto(Trip trip){
        return TripDTO.builder()
                .departureDate(trip.getDepartureDate().toString())
                .returnDate(trip.getReturnDate().toString())
                .numberOfDays(trip.getNumberOfDays())
                .build();

    }
}
