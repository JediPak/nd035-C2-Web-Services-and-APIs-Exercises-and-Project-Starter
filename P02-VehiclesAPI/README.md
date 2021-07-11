# ND035-P02-VehiclesAPI-Project

Project repository for JavaND Project 2, where students implement a Vehicles API using Java and Spring Boot that can communicate with separate location and pricing services.

## Instructions

Check each component to see its details and instructions. Note that all three applications
should be running at once for full operation. Further instructions are available in the classroom.

- [Vehicles API](vehicles-api/README.md)
- [Pricing Service](pricing-service/README.md)
- [Boogle Maps](boogle-maps/README.md)

## Dependencies

The project requires the use of Maven and Spring Boot, along with Java v11.

## Endpoints:
### Boogle
* http://localhost:9191/maps?lat=20.0&lon=30.0
### Eureka
* http://localhost:8761/ (eureka dashboard)
### Price
* http://localhost:8082/services/price?vehicleId=1 (REST API url example)
* http://localhost:8082/services/price/all (grab all prices)
* http://localhost:8082/services/price?vehicleId=3 (Microservice url example)
* http://localhost:8082/h2 (price db)
### Vehicle
* http://localhost:8080/cars/all
* http://localhost:8080/cars/1 (GET/DELETE/PUT)
* http://localhost:8080/cars/ (POST)
* http://localhost:8080/h2 (vehicle db)

### Swagger
* http://localhost:8080/swagger-ui
