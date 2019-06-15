package com.example.AKTours.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@Builder
@ToString
public class TripDTO {

//    private String DepartureDate;
//    private String ReturnDate;
    private int numberOfDays;
//    private String boardType;
//    private double adultPrice;
//    private double childrenPrice;
//    private double promoPrice;
    private int adultVacancy;
    private int childrenVacancy;
//    private Long hotelId;

}
