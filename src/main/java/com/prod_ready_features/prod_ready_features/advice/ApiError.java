package com.prod_ready_features.prod_ready_features.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
public class ApiError {

    private LocalDateTime timestamp;
    private String error;
    private HttpStatusCode httpStatusCode;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String error, HttpStatusCode httpStatusCode) {
        this();
        this.error = error;
        this.httpStatusCode = httpStatusCode;
    }
}
