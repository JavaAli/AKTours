package com.example.AKTours.model.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {

    private String departureDate;
    private String returnDate;
    private int numberOfDays;
    private String boardType;
    private double adultPrice;
    private double childrenPrice;
    private double promoPrice;
    private int adultVacancy;
    private int childrenVacancy;
    private Long hotelId;

    public TripDTO(String departureDate, String returnDate, int numberOfDays) {
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.numberOfDays = numberOfDays;
    }
}
