package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Country;
import com.example.AKTours.repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CountryServiceTest {

    private CountryService countryService;
    private Country countryOne;
    private Country countryTwo;
    List<Country> countries;

    @MockBean
    private CountryRepository countryRepository;

    @Before
    public void setUp() throws Exception {
        countryService = new CountryService(countryRepository);
        countryOne = new Country(1L, "Panama", null);
        countryTwo = new Country(2L, "Costa Rica", null);
        countries = new ArrayList<>();
        countries.add(countryOne);
        countries.add(countryTwo);
    }

    @Test
    public void findAll() {
        Mockito.when(countryRepository.findAll()).thenReturn(countries);
        List<Country> resultListOfCountries = countryService.findAll();
        assertThat(resultListOfCountries).contains(countryOne);
        assertThat(resultListOfCountries).contains(countryTwo);
        assertThat(resultListOfCountries.size()).isEqualTo(2);
    }

    @Test
    public void findByCountryName() {
        Mockito.when(countryRepository.findCountryByName(Mockito.anyString())).thenReturn(countryTwo);
        Country resultCountry = countryService.findByCountryName("Something");
        assertThat(resultCountry.getId()).isEqualTo(2L);
    }
}