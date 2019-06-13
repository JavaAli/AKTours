package com.example.AKTours.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@ControllerAdvice
public class ToursExceptionHandler {

   @ExceptionHandler(HotelNotFoundException.class)
    public ResponseEntity<ApiError> handleException(HotelNotFoundException ex, WebRequest request){
        ApiError response= ApiError.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.toString())
                .description(request.getDescription(false))
                .date(LocalDate.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExcpetionHandler(Exception ex, WebRequest request) {
        ApiError response= ApiError.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.toString())
                .description(request.getDescription(false))
                .date(LocalDate.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
