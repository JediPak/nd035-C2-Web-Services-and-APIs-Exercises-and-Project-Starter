/**
 * COMMENTING OUT SINCE USING SPRING DATA REST
 * REST API --> MICROSERVICE
 * CONTROLLER AND SERVICE IS NOT EXPLICITLY NEEDED ANYMORE
  */

// package com.udacity.pricing.api;
//
//import com.udacity.pricing.domain.price.Price;
//import com.udacity.pricing.service.PriceException;
//import com.udacity.pricing.service.PricingService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
///**
// * Implements a REST-based controller for the pricing service.
// */
//@RestController
//@RequestMapping("/services/price")
//public class PricingController {
//
//    /**
//     * Gets the price for a requested vehicle.
//     * @param vehicleId ID number of the vehicle for which the price is requested
//     * @return price of the vehicle, or error that it was not found.
//     */
//    private static final Logger logger = LogManager.getLogger(PricingController.class);
//
////http://localhost:8082/services/price?vehicleId=1 (REST API url example)
////http://localhost:8082/prices/3 (Microservice url example)
//    @GetMapping
//    public Price get(@RequestParam Long vehicleId) {
//        try {
//            return PricingService.getPrice(vehicleId);
//        } catch (PriceException ex) {
//            throw new ResponseStatusException(
//                    HttpStatus.NOT_FOUND, "Price Not Found", ex);
//        }
//
//    }
//}
