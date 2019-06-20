package com.example.AKTours.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
@JsonIgnoreProperties({"hotel"})
@ToString
@Builder
@Entity
@Table(name = "Trips")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Trip implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private String boardType;

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

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "hotel_id_pk")
    private Hotel hotel;

}
