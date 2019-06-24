package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.repository.HotelRepository;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class HotelServiceTest {

    private HotelService hotelService;
    private Hotel hotelOne;
    private Hotel hotelTwo;
    private List<Hotel> selectedHotels;
    private List<Hotel> allHotels;

    @MockBean
    private HotelRepository hotelRepository;

    @Before
    public void setUp() {
        hotelService = new HotelService(hotelRepository);
        hotelOne = Hotel.builder()
                .id(1L)
                .standard("4Stars")
                .name("Tropicana")
                .description("Hotel on Italy cost")
                .trips(null)
                .build();
        hotelTwo = Hotel.builder()
                .id(2L)
                .standard("3Stars")
                .name("Bambino")
                .description("Hotel in the centre of Naples")
                .trips(null)
                .build();
        selectedHotels = new ArrayList<>();
        allHotels = new ArrayList<>();
        selectedHotels.add(hotelTwo);
        allHotels.add(hotelOne);
        allHotels.add(hotelTwo);

    }

    @Test
    public void findAll() {
        Mockito.when(hotelRepository.findAll()).thenReturn(allHotels);
        List<Hotel> hotelsFound = hotelService.findAll();
        assertThat(hotelsFound.size()).isEqualTo(2);
    }

    @Test
    public void findHotelByStandard() throws EntityNotFoundException {
        Mockito.when(hotelRepository.findHotelByStandard(Mockito.any())).thenReturn(selectedHotels);
        List<Hotel> hotelsFound = hotelService.findHotelByStandard("4stars");
        assertThat(hotelsFound.get(0).getName()).isEqualTo(hotelTwo.getName());
    }

    @Test
    public void findByHotelName() throws EntityNotFoundException {
        Mockito.when(hotelRepository.findHotelByName(Mockito.anyString())).thenReturn(Optional.of(selectedHotels.get(0)));
        Hotel result = hotelService.findByHotelName("Bambino");
        assertThat(result.getId()).isEqualTo(2L);
    }
    @Test(expected = EntityNotFoundException.class)
    public void findNonExcistingHotel() throws EntityNotFoundException {
//        Mockito.when(hotelRepository.findHotelByName(Mockito.anyString())).thenReturn();
        Hotel result = hotelService.findByHotelName("XXX");
    }

}