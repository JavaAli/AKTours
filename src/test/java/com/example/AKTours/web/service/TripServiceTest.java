package com.example.AKTours.web.service;

import com.example.AKTours.model.dto.TripDTO;
import com.example.AKTours.model.entity.BoardType;
import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.model.entity.Trip;
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
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class TripServiceTest {
    private TripService tripService;
    private Hotel hotel;
    private Trip trip;
    private TripDTO tripDTO;
    private List<TripDTO> tripsDTO;
    private List<Trip> trips;;

    @MockBean
    private TripRepository tripRepository;

    @Before
    public void setUp() {
        tripService=new TripService(tripRepository);
        trip = Trip.builder()
                .returnDate(LocalDate.of(2019, 3, 3).plusWeeks(2))
                .departureDate(LocalDate.of(2019, 3, 3))
                .childrenVacancy(1)
                .adultVacancy(3)
                .childrenPrice(BigDecimal.valueOf(3000))
                .adultPrice(BigDecimal.valueOf(4000))
                .numberOfDays(14)
                .id(1L)
                .boardType(BoardType.BB)
                .promoPrice(BigDecimal.valueOf(3400))
                .build();
        trips=new ArrayList<>();
        trips.add(trip);
        hotel = Hotel.builder()
                .id(1L)
                .standard("4Stars")
                .name("Tropicana")
                .description("Hotel on Italy cost")
                .trips(null)
                .build();

        tripDTO=TripDTO.builder()
                .returnDate("2019-03-17")
                .departureDate("2019-03-03")
                .numberOfDays(14)
                .build();
tripsDTO=new ArrayList<>();
tripsDTO.add(tripDTO);

//        tripDTO=TripDTO.builder()
//                .adultPrice(4000)
//                .boardType("BB")
//                .adultVacancy(3)
//                .childrenVacancy(1)
//                .hotelId(1L)
//                .returnDate("2019-03-17")
//                .departureDate("2019-03-03")
//                .adultPrice(4000)
//                .childrenPrice(3000)
//                .promoPrice(3400)
//                .numberOfDays(14)
//                .build();
//

    }

    @Test
    public void findAllTrips() {
        Mockito.when(tripRepository.findAll()).thenReturn(trips);
        List<TripDTO> tripsFound = tripService.findAllTrips();
        assertThat(tripsFound.size()).isEqualTo(1);
        assertThat(tripsFound.get(0)).isEqualTo(tripDTO);
    }

    @Test
    public void findTripByHotelName() {
    }

    @Test
    public void convert() {
        TripDTO newDTO = tripService.convertTripToDto(trip);
        assertThat(newDTO.getAdultPrice()).isEqualTo(tripDTO.getAdultPrice());
        assertThat(newDTO.getDepartureDate()).isEqualTo(tripDTO.getDepartureDate());
        assertThat(newDTO.getReturnDate()).isEqualTo(tripDTO.getReturnDate());
    }
}