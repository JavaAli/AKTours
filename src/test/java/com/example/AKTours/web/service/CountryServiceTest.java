package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.City;
import com.example.AKTours.model.entity.Continent;
import com.example.AKTours.model.entity.Country;
import com.example.AKTours.repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CountryServiceTest {

    private CountryService countryService;
    private Country countryOne;
    private Country countryTwo;
    List<Country> countries;
    private Continent continent;

    @MockBean
    private CountryRepository countryRepository;

    @Before
    public void setUp() throws Exception {
        continent=new Continent("Middle America");
        countryService = new CountryService(countryRepository);
        countryOne = new Country("Panama",continent);
        countryTwo = new Country( "Costa Rica",continent);
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
        assertThat(resultCountry.getName()).isEqualTo("Costa Rica");
    }
    @Test
    public void findCountryByContinentName() {
        Mockito.when(countryRepository.findCountryByContinent_Name(Mockito.anyString())).thenReturn(countries);
        List<Country> resultCountry = countryService.findCountryByContinentName("Something");
        assertThat(resultCountry.get(0).getName()).isEqualTo("Panama");
        assertThat(resultCountry.size()).isEqualTo(2);
    }
}