package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.Trip;
import com.example.AKTours.web.service.TripService;
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
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

;
import static java.time.format.DateTimeFormatter.ofPattern;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TripController.class)
@RunWith(SpringRunner.class)
public class TripControllerTest {
    private Trip trip;
    private List<Trip> trips;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    @Before
    public void setUp() {
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
}