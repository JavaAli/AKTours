package com.example.AKTours.web.exceptions;

import java.time.LocalDateTime;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@ControllerAdvice
public class ToursExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        ApiError response = ApiError.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .description(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        log.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        String errorMessage = "Check values : ";
        for (ObjectError error : ex.getBindingResult().getAllErrors()
        ) {
            String fieldErrorMessage = error.getDefaultMessage();
            errorMessage = new StringBuilder().append(errorMessage).append(" ").append(fieldErrorMessage).toString();

        }
        ApiError response = ApiError.builder()
                .message(errorMessage)
                .status(HttpStatus.BAD_REQUEST)
                .description(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        log.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleException(EntityNotFoundException ex, WebRequest request) {
        ApiError response = ApiError.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .description(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        log.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DuplicateTripsException.class})
    public ResponseEntity<ApiError> handleException(DuplicateTripsException ex, WebRequest request) {
        ApiError response = ApiError.builder()
                .message(ex.getMessage())
                .status(HttpStatus.CONFLICT)
                .description(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        log.error(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExcpetionHandler(Exception ex) {
        ApiError response = ApiError.builder()
                .message(ex.getMessage())
                .description(ex.getLocalizedMessage())
                .timestamp(LocalDateTime.now())
                .build();
        log.error(ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
