package com.example.AKTours.web.controllers;

import com.example.AKTours.model.dtos.TripDto;
import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.web.exceptions.DuplicateTripsException;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import com.example.AKTours.web.service.TripService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.StringContains;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TripController.class)
@RunWith(SpringRunner.class)
public class TripControllerTest {
    private Trip trip;
    private TripDto tripDto;
    private List<Trip> trips;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        trip = Trip.builder()
                .returnDate(LocalDate.now().plusWeeks(2).plusDays(1))
                .departureDate(LocalDate.now().plusDays(1))
                .childrenVacancy(1)
                .adultVacancy(3)
                .childrenPrice(BigDecimal.valueOf(1500))
                .adultPrice(BigDecimal.valueOf(4000))
                .numberOfDays(14)
                .id(1L)
                .boardType("BB")
                .promoPrice(BigDecimal.valueOf(3400))
                .build();
        tripDto = TripDto.builder()
                .returnDate(LocalDate.now().plusWeeks(2).plusDays(1))
                .departureDate(LocalDate.now().plusDays(1))
                .childrenVacancy(1)
                .adultVacancy(3)
                .childrenPrice(1500)
                .adultPrice(4000)
                .numberOfDays(14)
                .boardType("BB")
                .promoPrice(3400)
                .hotel("Zacisze")
                .homeAirport("Balice")
                .destinAirport("Changi")
                .build();
        trips = new ArrayList<>();
        trips.add(trip);
    }

    @Test
    public void allTrips() throws Exception {
        Mockito.when(tripService.findAllTrips()).thenReturn(trips);
        mockMvc.perform(get("/trips"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].boardType", new StringContains("BB")));
    }

    @Test
    public void findTripByHotel() throws Exception {
        Mockito.when(tripService.findTripByHotelName("Hilton")).thenReturn(trips);
        mockMvc.perform(get("/tripsByHotel/Hilton"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].boardType", new StringContains("BB")));
    }

    @Test
    public void findTripByCity() throws Exception {
        Mockito.when(tripService.findTripByCityName("London")).thenReturn(trips);
        mockMvc.perform(get("/tripsByCity/London"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].boardType", new StringContains("BB")));
    }

    @Test
    public void findTripByCountry() throws Exception {
        Mockito.when(tripService.findTripByCountryName("Poland")).thenReturn(trips);
        mockMvc.perform(get("/tripsByCountry/Poland"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].boardType", new StringContains("BB")));
    }

    @Test
    public void findTripByContinent() throws Exception {
        Mockito.when(tripService.findTripByContinentName("Europe")).thenReturn(trips);
        mockMvc.perform(get("/tripsByContinent/Europe"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].boardType", new StringContains("BB")));
    }

    @Test
    public void findTripsByDate() throws Exception {
        Mockito.when(tripService.findTripByDate(LocalDate.of(2019, 6, 16))).thenReturn(trips);
        mockMvc.perform(get("/tripsByDate/2019-06-16"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].boardType", new StringContains("BB")));
    }

    @Test
    public void findTripsByPrice() throws Exception {
        Mockito.when(tripService.findTripByPrice(BigDecimal.valueOf(900))).thenReturn(trips);
        mockMvc.perform(get("/tripsByPrice/900"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].boardType", new StringContains("BB")));
    }

    @Test
    public void addNewTripWithSuccess() throws Exception {
        Mockito.when(tripService.addTrip(tripDto)).thenReturn(trip);
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("boardType", new StringContains("BB")));
    }

    @Test
    public void shouldThrowValidationErrorToAddNewTrip_DatesInPast() throws Exception {
        tripDto = TripDto.builder()
                .returnDate(LocalDate.now().plusWeeks(2).plusDays(1))
                .departureDate(LocalDate.now().minusDays(1))
                .childrenVacancy(1).adultVacancy(3)
                .childrenPrice(1500).adultPrice(4000).promoPrice(3400)
                .numberOfDays(16).boardType("BB")
                .hotel("Zacisze").homeAirport("Balice").destinAirport("Changi")
                .build();
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("ApiError.message",
                        new StringContains("Departure date shouldn't be in the past")));
    }

    @Test
    public void shouldThrowValidationErrorForAddNewTrip_WronNumberOfDays() throws Exception {
        tripDto = TripDto.builder()
                .returnDate(LocalDate.now().plusWeeks(2).plusDays(1))
                .departureDate(LocalDate.now().plusDays(1))
                .childrenVacancy(1).adultVacancy(3)
                .childrenPrice(1500).adultPrice(4000).promoPrice(3400)
                .numberOfDays(16).boardType("BB")
                .hotel("Zacisze").homeAirport("Balice").destinAirport("Changi")
                .build();
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("ApiError.message",
                        new StringContains("Number of days do not match depart and return date")));
    }

    @Test
    public void shouldThrowValidationErrorForAddNewTrip_WrongBoardType() throws Exception {
        tripDto = TripDto.builder()
                .returnDate(LocalDate.now().plusWeeks(2).plusDays(1))
                .departureDate(LocalDate.now().plusDays(1))
                .childrenVacancy(1).adultVacancy(3)
                .childrenPrice(1500).adultPrice(4000).promoPrice(3400)
                .numberOfDays(14).boardType("BBrty")
                .hotel("Zacisze").homeAirport("Balice").destinAirport("Changi")
                .build();
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("ApiError.message",
                        new StringContains("Board type should have only two capital letters")));
    }

    @Test
    public void shouldThrowValidationErrorForAddNewTrip_PromoPriceIsHigherThanNormal() throws Exception {
        tripDto = TripDto.builder()
                .returnDate(LocalDate.now().plusWeeks(2).plusDays(1))
                .departureDate(LocalDate.now().plusDays(1))
                .childrenVacancy(1).adultVacancy(3)
                .childrenPrice(1500).adultPrice(4000).promoPrice(5400)
                .numberOfDays(16).boardType("BB")
                .hotel("Zacisze").homeAirport("Balice").destinAirport("Changi")
                .build();
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("ApiError.message",
                        new StringContains("Sale price should be lower than ordinary price")));
    }

    @Test
    public void shouldThrowValidationErrorToAddNewTrip_LackOfMandatoryValue() throws Exception {
        tripDto = TripDto.builder()
                .returnDate(LocalDate.now().plusWeeks(2).plusDays(1))
                .departureDate(LocalDate.now().plusDays(1))
                .childrenVacancy(1).adultVacancy(3)
                .childrenPrice(1500).adultPrice(4000).promoPrice(3400)
                .numberOfDays(14).boardType("BB")
                .hotel("Zacisze").destinAirport("Changi")
                .build();
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("ApiError.message",
                        new StringContains("Field Home Airport is mandatory")));
    }

    @Test
    public void shouldThrowValidationErrorToAddNewTrip_TripZeroDaysLong() throws Exception {
        tripDto = TripDto.builder()
                .returnDate(LocalDate.now().plusDays(1))
                .departureDate(LocalDate.now().plusDays(1))
                .childrenVacancy(1).adultVacancy(3)
                .childrenPrice(1500).adultPrice(4000).promoPrice(3400)
                .numberOfDays(0).boardType("BB")
                .hotel("Zacisze").destinAirport("Changi").homeAirport("Pyrzowice")
                .build();
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("ApiError.message",
                        new StringContains("Trip should take minimum 1 day")));
    }

    @Test
    public void shouldThrowDuplicateErrorToAddNewTrip() throws Exception {
        Mockito.when(tripService.addTrip(tripDto)).thenThrow(DuplicateTripsException.class);
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripDto)))
                .andExpect(status().isConflict());
    }

    @Test
    public void shouldThrowEntityNotFound() throws Exception {
        Mockito.when(tripService.addTrip(tripDto)).thenThrow(EntityNotFoundException.class);
        mockMvc.perform(post("/trips")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tripDto)))
                .andExpect(status().isNotFound());
    }
}