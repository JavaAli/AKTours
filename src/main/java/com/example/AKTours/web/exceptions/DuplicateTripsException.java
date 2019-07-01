package com.example.AKTours.web.exceptions;

public class DuplicateTripsException extends RuntimeException {
    public DuplicateTripsException(String message) {
        super(message);
    }
}
