package com.udacity.boogle.maps;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is our actual REST controller for the application.
 * This implements what a GET request will respond with
 * - in our case, since it is a Mock of a WebService,
 * we are just responding with a random address from the repository.
 */
@RestController
@RequestMapping("/maps")
public class MapsController {

    @GetMapping
    // example url
    // http://localhost:9191/maps?lat=20.0&lon=30.0
    public Address get(@RequestParam Double lat, @RequestParam Double lon) {
        return MockAddressRepository.getRandom();
    }
}
