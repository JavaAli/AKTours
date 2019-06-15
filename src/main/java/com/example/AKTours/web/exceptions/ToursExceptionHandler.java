package com.example.AKTours.web.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.time.LocalDateTime;

@Log4j2
@ControllerAdvice
public class ToursExceptionHandler {

   @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleException(EntityNotFoundException ex, WebRequest request){
        ApiError response= ApiError.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .description(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
       log.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExcpetionHandler(Exception ex) {
        ApiError response= ApiError.builder()
                .message(ex.getMessage())
                .description(ex.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
        log.error(ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
