package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAll() {
        List<Hotel> collect = StreamSupport.stream(hotelRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return collect;
    }

    public List<Hotel> findHotelByStandard(String standard) {
        List<Hotel> collect = StreamSupport.stream(hotelRepository.findHotelByStandard(standard).spliterator(), false)
                .collect(Collectors.toList());
        return collect;
    }

    public Hotel findByHotelName(String name) {
        return hotelRepository.findHotelByName(name);
    }

    public List<Hotel> findHotelByCityName(String name) {

        String hilton = "London";
        String mariott = "Paris";
        String zacisze = "Wachock";
        String leCorusiere = "Teheran";
        if (hilton.equals(name)){
           return hotelRepository.findHotelsInLondon(name);
        } else if(mariott.equals(name)) {
            return hotelRepository.findHotelsInParis(name);
        }else if (zacisze.equals(name)){
            return hotelRepository.findHotelsInWachock(name);
        }else if (leCorusiere.equals(name)){
            return hotelRepository.findHotelsInTeheran(name);
        }else {
            return null;
        }


    }

}
