package com.example.AKTours.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Trip {
    String From;
    String To;
    LocalDate DepartureDate;
    LocalDate ReturnDate;
    int numberOfDays;
    Enum<BoardType> boardTypeEnum;
    BigDecimal adultPrice;
    BigDecimal childrenPrice;
    BigDecimal promoPrice;
    int adultVaconcy;
    int childrenVaconcy;

}
