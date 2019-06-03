package com.example.AKTours.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Trips")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "depart_date")
    private LocalDate DepartureDate;
    @Column(name = "return_date")
    private LocalDate ReturnDate;
    @Column(name = "number_days")
    private int numberOfDays;
    @Column(name = "board_type")
    private Enum<BoardType> boardTypeEnum;
    @Column(name = "adult_price")
    private BigDecimal adultPrice;
    @Column(name = "children_price")
    private BigDecimal childrenPrice;
    @Column(name = "promo_price")
    private BigDecimal promoPrice;
    @Column(name = "adult_vacancy")
    private int adultVacancy;
    @Column(name = "children_vacancy")
    private int childrenVacancy;

}
