package com.example.AKTours.model.dtos;

import java.time.LocalDate;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.time.temporal.ChronoUnit.DAYS;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class TripDto {

    @NotNull(message = "Field Departure Date is mandatory")
    private LocalDate departureDate;

    @NotNull(message = "Field Return Date is mandatory")
    private LocalDate returnDate;

    @Min(value = 1, message = "Trip should take minimum 1 day")
    @NotNull(message = "Field number of days is mandatory")
    private long numberOfDays;

    @NotNull(message = "Field Board Type is mandatory")
    @Pattern(regexp = "^[\\p{Lu}][\\p{Lu}]$", message = "Board type should have only two capital letters")
    private String boardType;

    @NotNull(message = "Field Adult Price is mandatory")
    @Min(value = 1, message = "Price shouldn't have negative value")
    @Max(value = 20000, message = "Adult price should be lower than 20000 pln")
    private double adultPrice;

    @NotNull(message = "Field Children Price is mandatory")
    @Min(value = 1, message = "Price shouldn't have negative value")
    private double childrenPrice;

    @NotNull(message = "Field Promo Price is mandatory")
    @Min(value = 1, message = "Price shouldn't have negative value")
    private double promoPrice;

    @NotNull(message = "Field Adult Vacancy is mandatory")
    @Min(value = 1, message = "Vacancy shouldn't be negative")
    @Max(value = 100, message = "Trips can have max 100 vacancies")
    private int adultVacancy;

    @NotNull(message = "Field Children Vacancy is mandatory")
    @Min(value = 0, message = "Vacancy shouldn't be negative")
    @Max(value = 100, message = "Trips can have max 100 vacancies")
    private int childrenVacancy;

    @NotBlank(message = "Field Hotel is mandatory")
    private String hotel;

    @NotBlank(message = "Field Home Airport is mandatory")
    private String homeAirport;

    @NotBlank(message = "Field Destination Airport is mandatory")
    private String destinAirport;

    @AssertTrue(message = "Trip should take minimum 1 day")
    private boolean isTripHasMinTerm() {
        return departureDate.isBefore(returnDate);
    }

    @AssertTrue(message = "Number of days do not match depart and return date")
    private boolean isNumberOfDaysCorrect() {
        return DAYS.between(departureDate, returnDate) == numberOfDays;
    }

    @AssertTrue(message = "Price for child should be lower than for an adult person")
    private boolean isChildrenPriceLowerThanForAdults() {
        return childrenPrice < adultPrice;
    }

    @AssertTrue(message = "Sale price should be lower than ordinary price")
    private boolean isPromoPriceLowerThanForAdults() {
        return promoPrice < adultPrice;
    }

    @AssertTrue(message = "Departure date shouldn't be in the past")
    private boolean isDepartureDateInFuture() {
        return departureDate.isAfter(LocalDate.now());
    }

    @AssertTrue(message = "Return date shouldn't be in the past")
    private boolean isReturnDateInFuture() {
        return returnDate.isAfter(LocalDate.now());
    }

    @AssertTrue(message = "Trip should take maximum three months")
    private boolean isTripShorterThanThreeMonths() {
        return returnDate.isBefore(departureDate.plusMonths(3));
    }
}
