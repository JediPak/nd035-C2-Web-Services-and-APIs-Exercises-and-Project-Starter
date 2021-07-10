package com.udacity.vehicles.api;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.Details;
import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import com.udacity.vehicles.service.CarBadRequestException;
import com.udacity.vehicles.service.CarService;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
//import javax.ws.rs.Produces;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/cars")
@ApiResponses(
        value={
                @ApiResponse(code = 201, message = "OK, and as a result, a resource has been created"),
                @ApiResponse(code = 204, message = "OK, and as a result, a resource has been deleted"),
                @ApiResponse(code = 400, message = "Bad request, follow the API documents"),
                @ApiResponse(code = 401, message = "Unauthorized"),
                @ApiResponse(code = 404, message = "Car Not Found with given Id"),
                @ApiResponse(code = 500, message = "server is down :(")
        }
)
class CarController {

    private final CarService carService;
    private final CarResourceAssembler assembler;

    CarController(CarService carService, CarResourceAssembler assembler) {
        this.carService = carService;
        this.assembler = assembler;
    }

    /**
     * Creates a list to store any vehicles.
     * @return list of vehicles
     */
//    @GetMapping
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Resources<Resource<Car>> list() {
        List<Resource<Car>> resources = carService.list().stream().map(assembler::toResource)
                .collect(Collectors.toList());

        Resources<Resource<Car>> resourceResourcesCar = new Resources<>(resources,
                linkTo(methodOn(CarController.class).list()).withSelfRel());

        System.out.println("CONTROLLER list(): "+ resourceResourcesCar.toString());

        return resourceResourcesCar;
//        return new Resources<>(resources,
//                linkTo(methodOn(CarController.class).list()).withSelfRel());
    }


    /**
     * Returns an example of the car object
     */
    @GetMapping(value = "/example", produces = MediaType.APPLICATION_JSON_VALUE)
//    @GetMapping("/example")
//    @Produces("application/json")
    Car get() {

        LocalDateTime localDateTime = LocalDateTime.now();

        Manufacturer manufacturer = new Manufacturer(100, "Audi");

        Details details = new Details();
        details.setBody("body");
        details.setModel("model");
        details.setManufacturer(manufacturer);
        details.setNumberOfDoors(3);
        details.setFuelType("fuel type");
        details.setEngine("engine");
        details.setMileage(123);
        details.setModelYear(2021);
        details.setProductionYear(2020);
        details.setExternalColor("blue");

        Location location = new Location(20.0, 30.0);
        location.setAddress("address");
        location.setCity("city");
        location.setState("state");
        location.setZip("zip");

        Car car = new Car();
        car.setPrice("10000");
        car.setCreatedAt(localDateTime);
        car.setModifiedAt(localDateTime);
        car.setCondition(Condition.NEW);
        car.setDetails(details);
        car.setLocation(location);

        return car;
//        return assembler.toResource(car);
    }

//    https://knowledge.udacity.com/questions/286730
//    @ApiOperation(value = "Returns Car details associated with the id")
//    @ApiResponses(value = {@ApiResponse(code = 200,message = "Succesfully retrieved car associated with id")})
//    @GetMapping("/{id}")
//    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
//    Resource<Car> get(@PathVariable Long id) {
//
//        return assembler.toResource(car);
//    }



    /**
     * Gets information of a specific car by ID.
     * @param id the id number of the given vehicle
     * @return all information for the requested vehicle
     */
//    @GetMapping("/{id}")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    @Produce({"MediaType.APPLICATION_JSON", "MediaType.APPLICATION_XML"})
//    @Produces("application/json")
//    @ApiOperation(value = "Returns Car details associated with the id")
//    @ApiResponses(value = {@ApiResponse(code = 200,message = "Succesfully retrieved car associated with id")})
//    Resource<Car> get(@PathVariable Long id) {
    Resource<Car> get(@PathVariable Long id) {
        /**
         * TODO: Use the `findById` method from the Car Service to get car information.
         * TODO: Use the `assembler` on that car and return the resulting output.
         *   Update the first line as part of the above implementing.
         */
//        return assembler.toResource(new Car());
        Resource<Car> carResource = assembler.toResource(carService.findById(id));
        System.out.println("CONTROLLER get(): "+ carResource.toString());
        return carResource;//assembler.toResource(carService.findById(id));
    }

    /**
     * Posts information to create a new vehicle in the system.
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {
        /**
         * TODO: Use the `save` method from the Car Service to save the input car.
         * TODO: Use the `assembler` on that saved car and return as part of the response.
         *   Update the first line as part of the above implementing.
         */
//        Resource<Car> resource = assembler.toResource(new Car());
        if(car.getId() != null){
            throw new CarBadRequestException("Error: To create a new car, please leave the <Id> null");
        }
        Resource<Car> resource = assembler.toResource(carService.save(car));

        //added for debugging
        if(resource == null){
            System.out.println("CONTROLLER post(): resource is null");
        }
        else{
            System.out.println("CONTROLLER post(): "+ resource.toString());
        }

        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    /**
     * Updates the information of a vehicle in the system.
     * @param id The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        /**
         * TODO: Set the id of the input car object to the `id` input.
         * TODO: Save the car using the `save` method from the Car service
         * TODO: Use the `assembler` on that updated car and return as part of the response.
         *   Update the first line as part of the above implementing.
         */
//        Resource<Car> resource = assembler.toResource(new Car());
        // checking to see if car with id already exists for not
        if(car.getId() == null){
            throw new CarBadRequestException("Error: Please provide the id of the car you are updating");
        }
        if(car.getId() != id){
            throw new CarBadRequestException("Error: <Id> endpoint path does not match with the <Id> in car request body");
        }
        if(!carService.exist(id)){
            throw new CarBadRequestException("Error: Car with id="+ id +" does not exist");
        }
        Resource<Car> resource = assembler.toResource(carService.save(car));
        return ResponseEntity.ok(resource);
    }

    /**
     * Removes a vehicle from the system.
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        /**
         * TODO: Use the Car Service to delete the requested vehicle.
         */
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
