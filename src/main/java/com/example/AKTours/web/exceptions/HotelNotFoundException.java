package com.example.AKTours.web.exceptions;

import com.example.AKTours.model.entity.Hotel;

public class HotelNotFoundException extends Exception {
    public HotelNotFoundException(String message) {
        super(message);
    }
}
