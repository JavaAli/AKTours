package com.example.AKTours.web.controllers;

import com.example.AKTours.model.entity.City;
import com.example.AKTours.model.entity.Continent;
import com.example.AKTours.model.entity.Country;
import com.example.AKTours.web.service.CityService;

import java.util.ArrayList;
import java.util.List;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CityController.class)
@RunWith(SpringRunner.class)
public class CityControllerTest {
    private City cityOne;
    private City cityTwo;
    List<City> cities;
    private Continent continent;
    private Country countryOne;
    private Country countryTwo;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CityService cityService;

    @Before
    public void setUp() {
        continent = new Continent("South America");
        countryOne = new Country("Panama", continent);
        countryTwo = new Country("Brazil", continent);
        cityOne = new City("Panama", countryOne);
        cityTwo = new City("Rio De Jeneiro", countryTwo);
        cities = new ArrayList<>();
        cities.add(cityOne);
        cities.add(cityTwo);
    }

    @Test
    public void cities() throws Exception {
        Mockito.when(cityService.findAll()).thenReturn(cities);
        mockMvc.perform(get("/cities"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name", new StringContains("Panama")));
    }

    @Test
    public void findByCityName() throws Exception {
        Mockito.when(cityService.findCityByName(Mockito.anyString())).thenReturn(cityOne);
        mockMvc.perform(get("/cities/name/Panama"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("name", new StringContains("Panama")));
    }

    @Test
    public void findCitiesByContinentName() throws Exception {
        Mockito.when(cityService.findCityByContinentName(Mockito.anyString())).thenReturn(cities);
        mockMvc.perform(get("/cities/byContinentName/South America"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name", new StringContains("Panama")));
    }

    @Test
    public void findCitiesByCountryName() throws Exception {
        Mockito.when(cityService.findCityByCountryName(Mockito.anyString())).thenReturn(cities);
        mockMvc.perform(get("/cities/byCountryName/Panama"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name", new StringContains("Panama")));
    }
}