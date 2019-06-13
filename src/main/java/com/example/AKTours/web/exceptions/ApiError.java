package com.example.AKTours.web.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ApiError {
    private String status;
    private String message;
    private LocalDate date;
    private String description;
}
