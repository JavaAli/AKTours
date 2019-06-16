package com.example.AKTours.model.entity;

public enum BoardType {
    BB("Bed and Breakfast"),
    HB("Half Board (Breakfast and Dinner normally)"),
    FB("Full Board (Beakfast, Lunch and Dinner)"),
    AI("All Inclusive (all services of full board plus any others specified in each case)"),
    RO("Room Only");

    private String description;

    BoardType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
