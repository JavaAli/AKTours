package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.City;
import com.example.AKTours.model.entity.Continent;
import com.example.AKTours.model.entity.Country;
import com.example.AKTours.repository.CityRepository;
import com.example.AKTours.web.exceptions.EntityNotFoundException;

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

@RunWith(SpringRunner.class)
public class CityServiceTest {

    private CityService cityService;
    private City cityOne;
    private City cityTwo;
    List<City> cities;
    private Continent continent;
    private Country countryOne;
    private Country countryTwo;

    @MockBean
    private CityRepository cityRepository;

    @Before
    public void setUp() throws Exception {
        cityService = new CityService(cityRepository);
        continent = new Continent("South America");
        countryOne = new Country("Panama", continent);
        countryTwo = new Country("Brazil", continent);
        cityOne = new City("Panama", countryOne);
        cityTwo = new City("Rio De Janeiro", countryTwo);
        cities = new ArrayList<>();
        cities.add(cityOne);
        cities.add(cityTwo);
    }

    @Test
    public void findAll() {
        Mockito.when(cityRepository.findAll()).thenReturn(cities);
        List<City> resultListOfCities = cityService.findAll();
        assertThat(resultListOfCities.contains(cityOne));
        assertThat(resultListOfCities.contains(cityTwo));
        assertThat(resultListOfCities.size()).isEqualTo(2);
    }

    @Test
    public void findCityByName() throws EntityNotFoundException {
        Mockito.when(cityRepository.findCityByName(Mockito.anyString())).thenReturn(cityOne);
        City resultCity = cityService.findCityByName("SomeName");
        assertThat(resultCity.getName()).isEqualTo("Panama");
    }

    @Test
    public void findCityByContinentName() throws EntityNotFoundException {
        Mockito.when(cityRepository.findCitiesByContinentName(Mockito.anyString())).thenReturn(cities);
        List<City> resultCity = cityService.findCityByContinentName("Something");
        assertThat(resultCity.get(0).getName()).isEqualTo("Panama");
        assertThat(resultCity.size()).isEqualTo(2);
    }
    @Test
    public void findCityByCountryName() throws EntityNotFoundException {
        Mockito.when(cityRepository.findCitiesByCountryName(Mockito.anyString())).thenReturn(cities);
        List<City> resultCity = cityService.findCityByCountryName("Something");
        assertThat(resultCity.get(0).getName()).isEqualTo("Panama");
        assertThat(resultCity.size()).isEqualTo(2);
    }
}