package com.udacity.pricing.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="(\"Cannot find price for Vehicle \" + vehicleId)")  // 404
public class PriceException extends Exception {

    public PriceException(String message) {
        super(message);
    }
}
