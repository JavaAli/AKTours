package com.example.AKTours.web.service;

import com.example.AKTours.model.dto.TripDTO;
import com.example.AKTours.model.entity.BoardType;
import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.repository.HotelRepository;
import com.example.AKTours.repository.TripRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class TripServiceTest {
    private Hotel hotel;
    private Trip trip;
    private TripDTO tripDTO;
    private TripService tripService;
    private List<TripDTO> tripDTOS;
    private List<Trip> tripList;
    @MockBean
    private TripRepository tripRepository;

    @Before
    public void setUp() {

        hotel = Hotel.builder()
                .id(1L)
                .standard("4Stars")
                .name("Tropicana")
                .description("Hotel on Italy cost")
                .trips(null)
                .build();
        trip = Trip.builder()
                .hotel(hotel)
                .ReturnDate(LocalDate.of(2019, 3, 3).plusWeeks(2))
                .DepartureDate(LocalDate.of(2019, 3, 3))
                .childrenVacancy(1)
                .adultVacancy(3)
                .childrenPrice(BigDecimal.valueOf(3000))
                .adultPrice(BigDecimal.valueOf(4000))
                .numberOfDays(14)
                .id(1L)
                .boardType(BoardType.BB)
                .promoPrice(BigDecimal.valueOf(3400))
                .build();
        tripList = new ArrayList<>();
        tripList.add(trip);

//        tripDTO=TripDTO.builder()
//                .adultPrice(4000)
//                .boardType("BB")
//                .adultVacancy(3)
//                .childrenVacancy(1)
//                .hotelId(1L)
//                .ReturnDate("2019-03-17")
//                .DepartureDate("2019-03-03")
//                .adultPrice(4000)
//                .childrenPrice(3000)
//                .promoPrice(3400)
//                .numberOfDays(14)
//                .build();
//

    }

    @Test
    public void findAllTrips() {
        Mockito.when(tripRepository.findAll()).thenReturn(tripList);

    }

    @Test
    public void findTripByHotelName() {
    }

    @Test
    public void convert() {
        TripDTO newDTO = tripService.convertTripToDto(trip);
//        assertEquals(newDTO.getAdultPrice(),tripDTO.getAdultPrice());
    }
}