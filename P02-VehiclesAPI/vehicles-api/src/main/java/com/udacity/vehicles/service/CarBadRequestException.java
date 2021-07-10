package com.udacity.vehicles.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)//, reason = "Car with id already exists")
public class CarBadRequestException extends RuntimeException {

    public CarBadRequestException() {
    }

    public CarBadRequestException(String message) {
        super(message);
    }
}
