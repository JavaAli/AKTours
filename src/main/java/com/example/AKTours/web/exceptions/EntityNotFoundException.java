package com.example.AKTours.web.exceptions;

import com.example.AKTours.model.entity.Hotel;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
