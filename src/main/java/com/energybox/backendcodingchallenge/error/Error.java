package com.energybox.backendcodingchallenge.error;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Error {

    private HttpStatus status;
    private Integer statusCode;
    private String message;

    public Error(HttpStatus status, Integer statusCode, String message) {
        super();
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
    }
}
