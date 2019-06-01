package com.example.AKTours.service;

import com.example.AKTours.model.entity.Hotel;
import com.example.AKTours.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAll(){
        List<Hotel> collect = StreamSupport.stream(hotelRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return collect;
    }




}
