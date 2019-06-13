package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.repository.HotelRepository;
import com.example.AKTours.web.exceptions.HotelNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Log4j2
@Service
public class HotelService {

    @Autowired
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAll() {
        log.info("Invoke hotel repisitory findAll ");
        List<Hotel> collect = StreamSupport.stream(hotelRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return collect;
    }

    public List<Hotel> findHotelByStandard(String standard) throws HotelNotFoundException {
        log.info("Invoke Hotel Repository findHotelByStandard using " + standard);
        if (!hotelRepository.findHotelByStandard(standard).isEmpty()) {
            List<Hotel> hotelCollected = StreamSupport.stream(hotelRepository.findHotelByStandard(standard).spliterator(), false)
                    .collect(Collectors.toList());
            return hotelCollected;
        } else {
            throw new HotelNotFoundException("Not found any hotel with " + standard + "standard");
        }
    }

    public List<Hotel> findByHotelName(String name) throws HotelNotFoundException {
        log.info("Invoke hotel repository findByHotelName using " + name);
        if (!hotelRepository.findHotelByName(name).isEmpty()) {
            List<Hotel> hotels = StreamSupport.stream(hotelRepository.findHotelByName(name)
                    .spliterator(), false)
                    .collect(Collectors.toList());
            return hotels;
        } else {
            throw new HotelNotFoundException("Not found any hotel with name: " + name);
        }
    }

    public List<Hotel> findHotelByCityName(String name) {
        log.info("Invoke Hotel Repository find hotels by city using " + name);
        String hilton = "London";
        String mariott = "Paris";
        String zacisze = "Wachock";
        String leCorusiere = "Teheran";

        if (hilton.equals(name)) {
            return hotelRepository.findHotelsInLondon(name);
        } else if (mariott.equals(name)) {
            return hotelRepository.findHotelsInParis(name);
        } else if (zacisze.equals(name)) {
            return hotelRepository.findHotelsInWachock(name);
        } else if (leCorusiere.equals(name)) {
            return hotelRepository.findHotelsInTeheran(name);
        } else {
            return null;
        }


    }

}
