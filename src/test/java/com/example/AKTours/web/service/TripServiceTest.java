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
        Mockito.when(tripRepository.findTripsByHotelName(Mockito.anyString())).thenReturn(trips);
        List<Trip> result = tripService.findTripByHotelName("Hilton");
        assertThat(result.size()).isEqualTo(1);
    }
    @Test
    public void findTripByCityName() throws EntityNotFoundException {
        Mockito.when(tripRepository.findTripsByCityName(Mockito.anyString())).thenReturn(trips);
        List<Trip> result = tripService.findTripByCityName("Paris");
        assertThat(result.size()).isEqualTo(1);
    }
    @Test(expected = EntityNotFoundException.class)
    public void findEmptyListForCities() throws EntityNotFoundException {
        Mockito.when(tripRepository.findTripsByCityName(Mockito.anyString())).thenReturn(new ArrayList<>());
        List<Trip> result = tripService.findTripByCityName("blebla");
    }

    @Test
    public void findTripByCountryName() throws EntityNotFoundException {
        Mockito.when(tripRepository.findTripsByCountryName(Mockito.anyString())).thenReturn(trips);
        List<Trip> result = tripService.findTripByCountryName("Poland");
        assertThat(result.size()).isEqualTo(1);
    }
    @Test(expected = EntityNotFoundException.class)
    public void findEmptyListForCountries() throws EntityNotFoundException {
        Mockito.when(tripRepository.findTripsByCountryName(Mockito.anyString())).thenReturn(new ArrayList<>());
        List<Trip> result = tripService.findTripByCountryName("Namibia");
    }
    @Test
    public void findTripByContinentName() throws EntityNotFoundException {
        Mockito.when(tripRepository.findTripsByContinentName(Mockito.anyString())).thenReturn(trips);
        List<Trip> result = tripService.findTripByContinentName("Europe");
        assertThat(result.size()).isEqualTo(1);
    }
    @Test(expected = EntityNotFoundException.class)
    public void findEmptyListForContinents() throws EntityNotFoundException {
        Mockito.when(tripRepository.findTripsByContinentName(Mockito.anyString())).thenReturn(new ArrayList<>());
        List<Trip> result = tripService.findTripByContinentName("Australia");
    }

    @Test
    public void   findTripsByDepartureDateAndReturnDateBetween()throws EntityNotFoundException{
        Mockito.when(tripRepository.findTripByDate(Mockito.any(LocalDate.class))).thenReturn(trips);
        List<Trip> result = tripService.findTripByDate(LocalDate.of(2019,6,1));
        assertThat(result.size()).isEqualTo(1);
    }

    @Test

    public void   findAllByAdultPriceLessThanEqual() throws EntityNotFoundException{
        Mockito.when(tripRepository.findAllByAdultPriceLessThanEqual(Mockito.any(BigDecimal.class))).thenReturn(trips);
        List<Trip> result = tripService.findTripByPrice(BigDecimal.valueOf(900));
        assertThat(result.size()).isEqualTo(1);
    }
}