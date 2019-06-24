package com.example.AKTours.web.service;

import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.repository.HotelRepository;
import com.example.AKTours.web.exceptions.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Hotel> findHotelByStandard(String standard) throws EntityNotFoundException {
        log.info("Invoke Hotel Repository findHotelByStandard using " + standard);
        if (!hotelRepository.findHotelByStandard(standard).isEmpty()) {
            List<Hotel> hotelCollected = StreamSupport.stream(hotelRepository.findHotelByStandard(standard).spliterator(), false)
                    .collect(Collectors.toList());
            return hotelCollected;
        } else {
            throw new EntityNotFoundException("Not found any hotel with " + standard + "standard");
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

    public Hotel findByHotelName(String name) throws EntityNotFoundException {
        log.info("Invoke hotel repository findByHotelName using " + name);
        Hotel hotel = hotelRepository.findHotelByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Hotel with name " + name + "not exist in base"));
        return hotel;
    }
}
