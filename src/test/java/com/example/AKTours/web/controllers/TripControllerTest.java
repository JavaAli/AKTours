package com.example.AKTours.web.controllers;

import com.example.AKTours.model.dto.TripDTO;
import com.example.AKTours.web.service.TripService;
import com.mysql.cj.x.protobuf.MysqlxResultset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TripController.class)
@RunWith(SpringRunner.class)
public class TripControllerTest {
    private TripDTO tripDTO;
    private List<TripDTO> tripsDTO;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TripService tripService;

    @Before
    public void setUp() {
        tripDTO = TripDTO.builder()
                .returnDate("2019-03-17")
                .departureDate("2019-03-03")
                .numberOfDays(14)
                .build();
        tripsDTO = new ArrayList<>();
        tripsDTO.add(tripDTO);

    }

    @Test
    public void allTrips() throws Exception {
        Mockito.when(tripService.findAllTrips()).thenReturn(tripsDTO);
        mockMvc.perform(get("/trips"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }
}