package com.example.AKTours.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Trip")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
