package com.example.springsecurity.demo.advice;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    public ApiError(HttpStatus statusCode, String error) {
        this();
        this.statusCode = statusCode;
        this.error = error;
    }

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    private LocalDateTime timestamp;
    private String error;
    private HttpStatus statusCode;
}
