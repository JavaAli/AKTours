package com.example.AKTours.web.exceptions;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
@AllArgsConstructor
@Builder
@Data
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)

public class ApiError {
    private HttpStatus status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String description;

    public ApiError() {
        timestamp=LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message="Unexpected error";
        this.description=ex.getLocalizedMessage();

    }
    public ApiError(HttpStatus status, Throwable ex, java.lang.String message) {
        this();
        this.status = status;
        this.message = message;
        this.description=ex.getLocalizedMessage();
    }
}
