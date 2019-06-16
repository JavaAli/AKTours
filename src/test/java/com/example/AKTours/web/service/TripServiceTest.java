package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.repository.TripRepository;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class TripServiceTest {
    private TripService tripService;
    private Hotel hotel;
    private Trip trip;
    private List<Trip> trips;

    @MockBean
    private TripRepository tripRepository;

    @Before
    public void setUp() {
        tripService=new TripService(tripRepository);
        trip = Trip.builder()
                .ReturnDate(LocalDate.of(2019, 3, 3).plusWeeks(2))
                .DepartureDate(LocalDate.of(2019, 3, 3))
                .childrenVacancy(1)
                .adultVacancy(3)
                .childrenPrice(BigDecimal.valueOf(3000))
                .adultPrice(BigDecimal.valueOf(4000))
                .numberOfDays(14)
                .id(1L)
                .boardType("BB")
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
    }

    @Test
    public void findAllTrips() throws EntityNotFoundException {
        Mockito.when(tripRepository.findAll()).thenReturn(trips);
        List<Trip> tripsFound = tripService.findAllTrips();
        assertThat(tripsFound.size()).isEqualTo(1);
        assertThat(tripsFound.get(0)).isEqualTo(trip);
    }
    @Test(expected = EntityNotFoundException.class)
    public void findEmptyList() throws EntityNotFoundException {
        Mockito.when(tripRepository.findAll()).thenReturn(new ArrayList<>());
        List<Trip> result = tripService.findAllTrips();
    }
    @Test
    public void findTripByHotelName() throws EntityNotFoundException {
        Mockito.when(tripRepository.findTripByHilton("Hilton")).thenReturn(trips);
        List<Trip> result = tripService.findTripByHotelName("Hilton");
    }

}