package com.example.AKTours.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TripDto {

    private LocalDate DepartureDate;
    private LocalDate ReturnDate;
    private int numberOfDays;
    private String boardType;
    private double adultPrice;
    private double childrenPrice;
    private double promoPrice;
    private int adultVacancy;
    private int childrenVacancy;
    private String hotel;
    private String homeAirport;
    private String destinAirport;

}
