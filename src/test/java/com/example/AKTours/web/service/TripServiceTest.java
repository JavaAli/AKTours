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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class TripServiceTest {

    private TripService tripService;
    private Trip trip;
    private TripDto tripDto;
    private List<Trip> trips;
    private Airport airport;
//    private Hotel hotel;
    @MockBean
    private TripRepository tripRepository;
    @MockBean
    private HotelRepository hotelRepository;
    @MockBean
    private AirportRepository airportRepository;
    @MockBean
    Hotel hotel;
    @Before
    public void setUp() {
        tripService = new TripService(tripRepository,airportRepository,hotelRepository);
        trips = new ArrayList<>();
        hotel = Hotel.builder()
                .id(1L)
                .standard("4Stars")
                .name("Tropicana")
                .description("Hotel on Italy cost")
                .trips(null)
                .build();
        tripDto = TripDto.builder()
                .ReturnDate(LocalDate.of(2019, 3, 3).plusWeeks(2))
                .DepartureDate(LocalDate.of(2019, 3, 3))
                .childrenVacancy(1)
                .adultVacancy(3)
                .childrenPrice(3000)
                .adultPrice(4000)
                .numberOfDays(14)
                .boardType("BB")
                .promoPrice(3400)
                .hotel("Zacisze")
                .homeAirport("Balice")
                .destinAirport("Changi")
                .build();
        airport = new Airport("Changi");
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
                .hotel(hotel)
                .homeAirport(airport)
                .destinAirport(airport)
                .build();
        trips.add(trip);

    }

    @Test
    public void findAllTrips() throws EntityNotFoundException {
        when(tripRepository.findAll()).thenReturn(trips);
        List<Trip> tripsFound = tripService.findAllTrips();
        assertThat(tripsFound.size()).isEqualTo(1);
        assertThat(tripsFound.get(0)).isEqualTo(trip);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findEmptyList() throws EntityNotFoundException {
        when(tripRepository.findAll()).thenReturn(new ArrayList<>());
        List<Trip> result = tripService.findAllTrips();
    }

    @Test
    public void findTripByHotelName() throws EntityNotFoundException {
        when(tripRepository.findTripsByHotelName(Mockito.anyString())).thenReturn(trips);
        List<Trip> result = tripService.findTripByHotelName("Hilton");
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findTripByCityName() throws EntityNotFoundException {
        when(tripRepository.findTripsByCityName(Mockito.anyString())).thenReturn(trips);
        List<Trip> result = tripService.findTripByCityName("Paris");
        assertThat(result.size()).isEqualTo(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findEmptyListForCities() throws EntityNotFoundException {
        when(tripRepository.findTripsByCityName(Mockito.anyString())).thenReturn(new ArrayList<>());
        List<Trip> result = tripService.findTripByCityName("blebla");
    }

    @Test
    public void findTripByCountryName() throws EntityNotFoundException {
        when(tripRepository.findTripsByCountryName(Mockito.anyString())).thenReturn(trips);
        List<Trip> result = tripService.findTripByCountryName("Poland");
        assertThat(result.size()).isEqualTo(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findEmptyListForCountries() throws EntityNotFoundException {
        when(tripRepository.findTripsByCountryName(Mockito.anyString())).thenReturn(new ArrayList<>());
        List<Trip> result = tripService.findTripByCountryName("Namibia");
    }

    @Test
    public void findTripByContinentName() throws EntityNotFoundException {
        when(tripRepository.findTripsByContinentName(Mockito.anyString())).thenReturn(trips);
        List<Trip> result = tripService.findTripByContinentName("Europe");
        assertThat(result.size()).isEqualTo(1);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findEmptyListForContinents() throws EntityNotFoundException {
        when(tripRepository.findTripsByContinentName(Mockito.anyString())).thenReturn(new ArrayList<>());
        List<Trip> result = tripService.findTripByContinentName("Australia");
    }

    @Test
    public void findTripsByDepartureDateAndReturnDateBetween() throws EntityNotFoundException {
        when(tripRepository.findTripByDate(any(LocalDate.class))).thenReturn(trips);
        List<Trip> result = tripService.findTripByDate(LocalDate.of(2019, 6, 1));
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findAllByAdultPriceLessThanEqual() throws EntityNotFoundException {
        when(tripRepository.findAllByAdultPriceLessThanEqual(any(BigDecimal.class))).thenReturn(trips);
        List<Trip> result = tripService.findTripByPrice(BigDecimal.valueOf(900));
        assertThat(result.size()).isEqualTo(1);
    }

//    @Test
//    public void addTripWithSuccess() throws EntityNotFoundException {
//        when(hotelRepository.findHotelByName(anyString())).thenReturn(Optional.ofNullable(hotel));
//        when(airportRepository.findAirportByName(anyString())).thenReturn(Optional.ofNullable(airport));
//        when(tripRepository.save(any())).thenReturn(trip);
//        doNothing().when(mock(Hotel.class)).addTripToHotels(any(Trip.class));
//        doNothing().when(mock(Airport.class)).addTripToTripsHome(any(Trip.class));
//        doNothing().when(mock(Airport.class)).addTripToTripsDest(any(Trip.class));
//        Trip result = tripService.addTrip(tripDto);
//        assertThat(result.getDepartureDate()).isEqualTo(trip.getDepartureDate());
//    }

    @Test
    public void findByHotelName() throws EntityNotFoundException {
        when(hotelRepository.findHotelByName(anyString())).thenReturn(Optional.of(hotel));
        Hotel result = tripService.findByHotelName("Bambino");
        assertThat(result.getId()).isEqualTo(1L);
    }
    @Test
    public void findByAirportName() throws EntityNotFoundException {
        when(airportRepository.findAirportByName(anyString())).thenReturn(Optional.of(airport));
        Airport result = tripService.findAirportByName("Some");
        assertThat(result.getName()).isEqualTo("Changi");
    }
}