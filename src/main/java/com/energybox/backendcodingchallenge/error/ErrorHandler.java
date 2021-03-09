package com.energybox.backendcodingchallenge.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.HttpURLConnection;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        ex.printStackTrace();
        Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, HttpURLConnection.HTTP_INTERNAL_ERROR, ex.getLocalizedMessage());
        return new ResponseEntity<>(error, new HttpHeaders(), error.getStatus());
    }


}
